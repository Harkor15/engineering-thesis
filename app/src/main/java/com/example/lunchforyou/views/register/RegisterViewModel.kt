package com.example.lunchforyou.views.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.Restaurant
import com.example.lunchforyou.utils.AuthService
import com.example.lunchforyou.utils.LocalDataManager
import com.example.lunchforyou.utils.RegisterCallback
import com.example.lunchforyou.utils.Utils
import kotlinx.coroutines.launch

class RegisterViewModel:RegisterCallback,ViewModel() {
    private var createAsRestaurant = false
    val registrationInfo = MutableLiveData<Int>()
    val registerResult = MutableLiveData<Boolean>()

    fun signUp(email: String, password: String, password2: String, asRestaurant:Boolean){
        createAsRestaurant=asRestaurant
        if(password!=password2)
            registrationInfo.value=R.string.passwords_are_not_the_same
        else{
            if(password.length<8)
                registrationInfo.value=R.string.password_must_be_at_least_8_characters
            else {
                if (!Utils.isValidEmail(email))
                    registrationInfo.value = R.string.email_is_not_correct
                else {
                    val auth = AuthService()
                    auth.createNewUser(email, password, this)
                }
            }
        }
    }

    override fun onUserCreateResult(uid: String) {
        if(createAsRestaurant){
            createNewRestaurant(AuthService().checkCurrentUser())
        }
        registerResult.value=true
    }

    override fun onUserCreateFailure() {
        registrationInfo.value= R.string.registration_failure
    }

    private fun createNewRestaurant(token:String?){
        if(token!=null) {
            viewModelScope.launch {
                if(Restaurant.create(token)) {
                    val localDataManager = LocalDataManager()
                    localDataManager.setIsUserRestaurant(true)
                    localDataManager.setUserToken(token)
                }
            }

        }
    }
}






