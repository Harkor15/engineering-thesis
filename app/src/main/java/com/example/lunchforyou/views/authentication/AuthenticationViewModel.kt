package com.example.lunchforyou.views.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.Client
import com.example.lunchforyou.database.Restaurant
import com.example.lunchforyou.utils.LocalDataManager
import com.example.lunchforyou.utils.AuthService
import com.example.lunchforyou.utils.SignInCallback
import kotlinx.coroutines.launch

class AuthenticationViewModel:SignInCallback, ViewModel() {
    val signInInfo = MutableLiveData<Int>()
    val navigateToClientMenu = MutableLiveData<Boolean>()
    val navigateToRestaurantMenu = MutableLiveData<Boolean>()
    val navigateToNewClientMenu = MutableLiveData<Boolean>()
    val showToast = MutableLiveData<Int>()

    fun init(){
        val userToken = LocalDataManager().getUserToken()
        if(!userToken.isNullOrBlank()){
            val localDataManager = LocalDataManager()
            val isRestaurant = LocalDataManager().isUserRestaurant()
            if(isRestaurant){
                viewModelScope.launch {
                    val restaurant = Restaurant.read(userToken)
                    if(restaurant==null){
                        localDataManager.clearAllData()
                    }else{
                        navigateToRestaurantMenu.value=true
                    }
                }
            }else{
                viewModelScope.launch {
                    val client = Client.read(userToken)
                    if(client==null){
                        localDataManager.clearAllData()
                    }else{
                        navigateToClientMenu.value=true
                    }
                }
            }
        }
    }

    fun signIn(login: String, password: String){
        if(password.isEmpty() or login.isEmpty()){
            signInInfo.value=R.string.enter_login_and_password
        }else{
            val auth = AuthService()
            auth.signIn(login,password,this)
        }
    }

    override fun onUserSignInResult(uid: String) {
        viewModelScope.launch {
            val localDataManager = LocalDataManager()
            val client = Client.read(uid)
            if(client==null){
                val restaurant = Restaurant.read(uid)
                if(restaurant==null){
                    navigateToNewClientMenu.value=true
                }else{
                    localDataManager.setIsUserRestaurant(true)
                    localDataManager.setUserToken(uid)
                    navigateToRestaurantMenu.value=true
                }
            }else{
                if(client.subscribedRestaurantToken!=null)
                    navigateToClientMenu.value=true
                else {
                    navigateToNewClientMenu.value = true
                    localDataManager.setIsUserRestaurant(false)
                    localDataManager.setUserToken(uid)
                }
            }
        }
    }

    override fun onUserSignInFailure() {
        signInInfo.value= R.string.login_failure
    }

}