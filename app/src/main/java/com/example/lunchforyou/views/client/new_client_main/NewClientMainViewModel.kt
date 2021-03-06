package com.example.lunchforyou.views.client.new_client_main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.database.Client
import com.example.lunchforyou.database.Restaurant
import com.example.lunchforyou.utils.LocalDataManager
import com.example.lunchforyou.utils.AuthService
import kotlinx.coroutines.launch

class NewClientMainViewModel:ViewModel() {
    var logout = MutableLiveData<Boolean>()
    var forceToFillClientDetails = MutableLiveData<Boolean>()
    var navigateToMainClient = MutableLiveData<Boolean>()
    var navigateToMainRestaurant = MutableLiveData<Boolean>()
    var subscribedRestaurant = MutableLiveData<String>()
    var saveClientPersonalDataResult = MutableLiveData<Boolean>()
    //var saveRestaurantResult = MutableLiveData<Boolean>()
    var displayClientPersonalData = MutableLiveData<Client>()
    var client: Client? = null
    private val token = AuthService().checkCurrentUser()

    fun init(){
        viewModelScope.launch {
            if(token==null){
                logout
            }else{
                client = Client.read(token)
                if (client==null)
                    forceToFillClientDetails.value=true
                else{
                    displayClientPersonalData.value=client!!
                }
            }
        }
    }

    fun logout(){
        LocalDataManager().clearAllData()
        AuthService().signOut()
        logout.value=true
    }

    fun setClientPersonalData(name: String, surname:String, address: String){
        if(name.isNotBlank() and surname.isNotBlank() and address.isNotBlank()){
            if(client==null ){
                viewModelScope.launch {
                    if(token!=null) {
                        val createResult = Client.create(token, name, surname, address, null)
                        if(createResult){
                            saveClientPersonalDataResult.value=true
                            forceToFillClientDetails.value=false
                            client = Client.read(token)
                        }
                    }else{
                        client!!.name = name
                        client!!.surname = surname
                        client!!.address=address
                        val result=client!!.update()
                        saveClientPersonalDataResult.value=result

                    }
                }
            }
        }
    }

    fun setSubscribedRestaurantToken(restaurantToken:String){
        if(restaurantToken.isNotBlank()) {
            viewModelScope.launch {
                if (client != null) {
                    val restaurant = Restaurant.read(restaurantToken)
                    if (restaurant != null) {
                        client!!.subscribedRestaurantToken = restaurantToken
                        val result = client!!.update()
                        if(result) {
                            subscribedRestaurant.value = restaurant.name
                            val localDataManager = LocalDataManager()
                            localDataManager.setSubscribedRestaurantToken(restaurantToken)
                            localDataManager.setIsUserRestaurant(false)
                            localDataManager.setUserToken(client!!.token!!)
                            navigateToMainClient.value = true
                        }
                    }else{
                        //saveRestaurantResult.value=false
                    }
                }else{
                    //saveRestaurantResult.value=false
                }
            }
        }else{
            //saveRestaurantResult.value=false
        }
    }
}