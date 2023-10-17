package com.example.androidpermissonhandlerdemo.API

import com.example.androidpermissonhandlerdemo.models.UserListModel
import kotlinx.coroutines.flow.Flow
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
   //TODO : FIRST WAY
    @GET("users")
    suspend fun getUSerList() : List<UserListModel> //or Respone or Call when return object else list can use here

    //TODO : SECOND WAY USING FLOW

//    @GET("users")
//    fun getUserList(): Flow<List<UserListModel>>

}