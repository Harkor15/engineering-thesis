package com.example.lunchforyou.views.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.Client
import com.example.lunchforyou.database.Restaurant
import com.example.lunchforyou.database.Subscription
import com.example.lunchforyou.utils.LocalDataManager
import com.example.lunchforyou.utils.AuthService
import com.example.lunchforyou.utils.SignInCallback
import kotlinx.coroutines.launch
import java.util.*
import kotlin.time.days

class AuthenticationViewModel:SignInCallback, ViewModel() {
    val signInInfo = MutableLiveData<Int>()
    val navigateToClientMenu = MutableLiveData<Boolean>()
    val navigateToRestaurantMenu = MutableLiveData<Boolean>()
    val navigateToNewClientMenu = MutableLiveData<Boolean>()
    val showToast = MutableLiveData<Int>()
    private val localDataManager = LocalDataManager()

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

    override fun onUserSignInResult(token: String) {
        viewModelScope.launch {

            val client = Client.read(token)
            if(client==null){
                val restaurant = Restaurant.read(token)
                if(restaurant==null){
                    navigateToNewClientMenu.value = true
                }else{
                    handleRestaurant(token)
                }
            }else{
                if(client.subscribedRestaurantToken!=null) {
                    handleClient(token, client.subscribedRestaurantToken!!)
                }
                else {
                    handleNewClient(token)
                }
            }
        }
    }

    private suspend fun handleNewClient(token: String) {
        localDataManager.setIsUserRestaurant(false)
        localDataManager.setUserToken(token)
        val subscription = Subscription.readClientSubscription(token)
        if (subscription?.lastDayOfSub != null) {
            val calendar = Calendar.getInstance()
            calendar.time = subscription.lastDayOfSub!!
            localDataManager.setSubscriptionExpiration(
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR),
            )
        }
        navigateToNewClientMenu.value = true
    }

    private fun handleClient(token:String, subscribedRestaurantToken:String){
        localDataManager.setIsUserRestaurant(false)
        localDataManager.setUserToken(token)
        viewModelScope.launch {
            val subscription = Subscription.readClientSubscription(token)
            if (subscription?.lastDayOfSub != null) {
                val calendar = Calendar.getInstance()
                calendar.time = subscription.lastDayOfSub!!
                localDataManager.setSubscriptionExpiration(
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.YEAR),
                )
            }
        }
        localDataManager.setSubscribedRestaurantToken(subscribedRestaurantToken)
        navigateToClientMenu.value = true
    }

    private fun handleRestaurant(token: String){
        localDataManager.setIsUserRestaurant(true)
        localDataManager.setUserToken(token)
        navigateToRestaurantMenu.value=true
    }

    override fun onUserSignInFailure() {
        signInInfo.value= R.string.login_failure
    }

}