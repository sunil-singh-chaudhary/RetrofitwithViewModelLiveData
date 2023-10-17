package com.example.androidpermissonhandlerdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidpermissonhandlerdemo.API.UserViewModel
import com.example.androidpermissonhandlerdemo.permision.PermissionUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: UserViewModel
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // The user has granted the location permissions.
            } else {
                // The user has denied the location permissions.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, PermissionUtils.FINE_LOCATION_PERMISSION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, PermissionUtils.ACCESS_BACKGROUND_LOCATION_PERMISSION)
                ) {
                    // The user has already denied the permissions.
                    // Explain to the user why you need the permissions
                    // and ask the user to go to the app settings to grant the permissions.
                } else {
                    // The user has denied the permissions and selected the "Don't ask again" checkbox.
                    // Show a message to the user that they need to grant the permissions in the app settings.
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hasAllPermissions = PermissionUtils.hasLocationPermission(this)
        Log.e("PERMISSION","$hasAllPermissions ")
        if (!hasAllPermissions) {
            var backgroudpermisson=PermissionUtils.hasBackgroundLocationPermission(this)
            Log.e("PERMISSION BG","$backgroudpermisson ")

            if(!backgroudpermisson){
                PermissionUtils.requestLocationPermissions(this)
            }else{
                Log.e("TAG","Background Permission Granted")

            }
        }else{
            Log.e("TAG","location Permission Granted")
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
}
