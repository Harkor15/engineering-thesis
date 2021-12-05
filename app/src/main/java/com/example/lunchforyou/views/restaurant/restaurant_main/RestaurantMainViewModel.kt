package com.example.lunchforyou.views.restaurant.restaurant_main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lunchforyou.utils.AuthService
import com.example.lunchforyou.utils.LocalDataManager

class RestaurantMainViewModel: ViewModel() {
    private val localDataManager = LocalDataManager()

    val backToLoginFragment = MutableLiveData<Boolean>()
    val onlyConfigurationPossible = MutableLiveData<Boolean>()

    fun init(){
        onlyConfigurationPossible.value = localDataManager.getIsRestaurantConfigured().not()
    }

    fun logout() {
        AuthService().signOut()
        localDataManager.clearAllData()
        backToLoginFragment.value=true
    }
}