package com.example.lunchforyou.views.client.client_main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lunchforyou.utils.AuthService
import com.example.lunchforyou.utils.LocalDataManager

class ClientMainViewModel:ViewModel() {
    val backToLoginFragment = MutableLiveData<Boolean>()
    private val localDataManager = LocalDataManager()

    fun init(){
        if(localDataManager.getUserToken()==null){
            localDataManager.clearAllData()
            backToLoginFragment.value=true
        }
    }

    fun logout(){
        AuthService().signOut()
        localDataManager.clearAllData()
        backToLoginFragment.value=true
    }
}