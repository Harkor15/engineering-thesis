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
                val restaurant = Restaurant.Read(uid)
                if(restaurant==null){
                    if(Client.create(uid,"","","",null))
                        navigateToClientMenu.value=true
                    else{
                    //TODO Database error while creating new user
                    }
                }else{
                    navigateToRestaurantMenu.value=true
                }
            }else{
                navigateToClientMenu.value=true
            }
        }
    }

    override fun onUserSignInFailure() {
        signInInfo.value= R.string.login_failure
    }

}