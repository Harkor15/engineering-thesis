package com.example.lunchforyou.views.authentication

import androidx.lifecycle.MutableLiveData
import com.example.lunchforyou.R
import com.example.lunchforyou.utils.AuthService
import com.example.lunchforyou.utils.SignInCallback

class AuthenticationViewModel:SignInCallback {
    val signInInfo = MutableLiveData<Int>()
    val signInResult = MutableLiveData<Boolean>()

    fun signIn(login: String, password: String){
        if(password.isEmpty() or login.isEmpty()){
            signInInfo.value=R.string.enter_login_and_password
        }else{
            val auth = AuthService()
            auth.signIn(login,password,this)
        }
    }

    override fun onUserSignInResult(uid: String) {
        signInResult.value=true
    }

    override fun onUserSignInFailure() {
        signInResult.value=false
        signInInfo.value= R.string.login_failure
    }

}