package com.example.lunchforyou.views.restaurant.restaurant_details_for_restaurant

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.Restaurant
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch

class RestaurantDetailsForRestaurantViewModel: ViewModel() {
    val response = MutableLiveData<Int>()
    val restaurantInfo = MutableLiveData<Restaurant>()

    fun init(){
        val token = LocalDataManager().getUserToken()
        if (token != null) {
            viewModelScope.launch {
                val response = Restaurant.read(token)
                if(response!=null)
                    restaurantInfo.value=response!!
            }
        }
    }

    fun saveRestaurantDetails(restaurantName: String, subscriptionPrice:String,
    address:String, deliveryHours:String, openHours:String){
        if(restaurantName.isBlank()or subscriptionPrice.isBlank() or
                address.isBlank() or deliveryHours.isBlank() || openHours.isBlank()){
            response.value = R.string.fill_in_the_balnks
        }else{
            if(!subscriptionPrice.isDigitsOnly()){
                response.value= R.string.subscription_price_is_not_correct
            }else{
                if(restaurantInfo.value!=null) {
                    viewModelScope.launch {
                        restaurantInfo.value!!.name = restaurantName
                        restaurantInfo.value!!.subscriptionPrice = subscriptionPrice
                        restaurantInfo.value!!.address = address
                        restaurantInfo.value!!.deliveryHours = deliveryHours
                        restaurantInfo.value!!.openedHours = openHours
                        if (restaurantInfo.value!!.update()) {
                            response.value = R.string.personal_data_saved
                            LocalDataManager().setIsRestaurantConfigured(true)
                        }
                        else
                            response.value = R.string.error
                    }
                }
            }
        }
    }
}