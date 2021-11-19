package com.example.lunchforyou.views.client.client_restaurant_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.database.Restaurant
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch

class ClientRestaurantDetailsViewModel:ViewModel() {
    val restaurantInfo = MutableLiveData<Restaurant>()

    fun init(){
        val restaurantId = LocalDataManager().getSubscribedRestaurantToken()
        if(!restaurantId.isNullOrBlank()) {
            viewModelScope.launch {
                val result = Restaurant.read(restaurantId)
                if(result!=null)
                    restaurantInfo.value=result!!
            }
        }
    }
}