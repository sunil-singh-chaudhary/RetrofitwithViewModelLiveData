package com.example.androidpermissonhandlerdemo.API

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpermissonhandlerdemo.models.UserListModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
//TODO : FIRST WAY USING LIVEMODEL

class UserViewModel : ViewModel() {
    private val _userList = MutableLiveData<List<UserListModel>>()
    val userList: LiveData<List<UserListModel>> = _userList

    var userApiInstance= RetrofitHelper.getInstance().create(UserApi::class.java)

    init {
        viewModelScope.launch {
            var result= userApiInstance.getUSerList()
            _userList.postValue(result)
        }
        }
    }

//TODO : SECOND WAY USING FLOW

//class UserViewModel : ViewModel() {
//    private val userApiInstance = RetrofitHelper.getInstance().create(UserApi::class.java)
//
//    // Use StateFlow to store the user list
//    private val _userList = MutableStateFlow<List<UserListModel>>(emptyList())
//    val userList: StateFlow<List<UserListModel>> = _userList
//
//    init {
//        // Launch a coroutine to fetch and update data
//        GlobalScope.launch {
//            userApiInstance.getUserList().collect { userList ->
//                _userList.value = userList
//            }
//        }
//    }
//}
