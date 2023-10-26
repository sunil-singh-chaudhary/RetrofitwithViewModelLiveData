package com.example.androidpermissonhandlerdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidpermissonhandlerdemo.API.UserViewModel
import com.example.androidpermissonhandlerdemo.permision.showGrantedToast
import com.example.androidpermissonhandlerdemo.permision.showPermanentlyDeniedDialog
import com.example.androidpermissonhandlerdemo.permision.showRationaleDialog
import com.fondesa.kpermissions.PermissionStatus
import com.fondesa.kpermissions.allGranted
import com.fondesa.kpermissions.anyPermanentlyDenied
import com.fondesa.kpermissions.anyShouldShowRationale
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.request.PermissionRequest

class MainActivity : AppCompatActivity() ,PermissionRequest.Listener{
    private lateinit var viewModel: UserViewModel
    //TODO :LOCATION INIT
    private val request by lazy {
        permissionsBuilder(android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Build the request with the permissions you would like to request and send it.
        request.addListener(this)

        findViewById<View>(R.id.btn_test_activity_permissions).setOnClickListener {
            request.send()
        }



        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        //TODO : FIRST WAY USING FLOW

        viewModel.userList.observe(this@MainActivity, Observer { userList ->
            // Update the UI with the new list of users
            Log.e("User-List-Obsorving->", "${userList[0].address!!.city}" )

        })

        //TODO : SECOND WAY USING FLOW WHEN we dont have lifecycle like above we use viewmodel so dont need
        // FLow

//        GlobalScope.launch {
//            viewModel.userList.collect { userList ->
//                // Update your UI with the new user list
//                // userList is automatically updated when data changes
//                Log.e("FLow Get", "$userList" )
//            }
//        }

    }

    override fun onPermissionsResult(result: List<PermissionStatus>) {
        when {
            result.anyPermanentlyDenied() -> showPermanentlyDeniedDialog(result)
            result.anyShouldShowRationale() -> showRationaleDialog(result, request)
            result.allGranted() -> showGrantedToast(result)
        }
    }
}
