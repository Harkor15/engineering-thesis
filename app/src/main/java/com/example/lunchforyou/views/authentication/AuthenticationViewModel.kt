package com.example.lunchforyou.views.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.Client
import com.example.lunchforyou.database.Restaurant
import com.example.lunchforyou.utils.AuthService
import com.example.lunchforyou.utils.SignInCallback
import kotlinx.coroutines.launch

class AuthenticationViewModel:SignInCallback, ViewModel() {
    val signInInfo = MutableLiveData<Int>()
    val navigateToClientMenu = MutableLiveData<Boolean>()
    val navigateToRestaurantMenu = MutableLiveData<Boolean>()
    val navigateToNewClientMenu = MutableLiveData<Boolean>()
    val showToast = MutableLiveData<Int>()

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
            val client = Client.read(uid)
            if(client==null){
                val restaurant = Restaurant.read(uid)
                if(restaurant==null){
                    navigateToNewClientMenu.value=true
                }else{
                    navigateToRestaurantMenu.value=true
                }
            }else{
                if(client.subscribedRestaurantToken!=null)
                    navigateToClientMenu.value=true
                else
                    navigateToNewClientMenu.value=true
            }
        }
    }

    override fun onUserSignInFailure() {
        signInInfo.value= R.string.login_failure
    }

}