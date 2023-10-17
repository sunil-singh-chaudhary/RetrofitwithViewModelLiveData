package com.example.androidpermissonhandlerdemo.API

import com.example.androidpermissonhandlerdemo.constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory()) // Add this line

            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }


}