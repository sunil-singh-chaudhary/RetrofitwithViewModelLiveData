package com.example.androidpermissonhandlerdemo.permision

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat

object PermissionUtils {

     const val ACCESS_BACKGROUND_LOCATION_PERMISSION = android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
     const val FINE_LOCATION_PERMISSION = android.Manifest.permission.ACCESS_FINE_LOCATION
     const val COARSE_LOCATION_PERMISSION = android.Manifest.permission.ACCESS_COARSE_LOCATION

    fun hasAllLocationPermissions(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(context, ACCESS_BACKGROUND_LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, FINE_LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, COARSE_LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED
    }

    fun hasLocationPermission(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(context, FINE_LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, COARSE_LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED
    }

    fun hasBackgroundLocationPermission(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(context, ACCESS_BACKGROUND_LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermissions(activity: Activity) {
        Log.e("PERMISSION RQG","Requesting ")

        val permissionsToRequest = mutableListOf<String>()

        if (ActivityCompat.checkSelfPermission(activity, ACCESS_BACKGROUND_LOCATION_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                permissionsToRequest.add(ACCESS_BACKGROUND_LOCATION_PERMISSION)

            }
        }

        if (ActivityCompat.checkSelfPermission(activity, FINE_LOCATION_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(FINE_LOCATION_PERMISSION)
        }

        if (ActivityCompat.checkSelfPermission(activity, COARSE_LOCATION_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(COARSE_LOCATION_PERMISSION)
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(activity, permissionsToRequest.toTypedArray(), 1)
        }
    }

    fun requestBackgroundLocationPermission(activity: Activity) {
        if (ActivityCompat.checkSelfPermission(activity, ACCESS_BACKGROUND_LOCATION_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(ACCESS_BACKGROUND_LOCATION_PERMISSION), 1)
        }
    }
}
