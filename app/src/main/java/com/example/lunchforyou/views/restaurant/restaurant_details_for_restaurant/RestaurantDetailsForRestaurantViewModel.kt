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
    val restaurantName = MutableLiveData<String>()
    val restaurantSubscriptionPrice = MutableLiveData<String>()
    val restaurantAddress = MutableLiveData<String>()
    val restaurantDeliveryHours = MutableLiveData<String>()
    val restaurantOpenHours = MutableLiveData<String>()


    lateinit var  restaurant:Restaurant

    fun init(){
        val token = LocalDataManager().getUserToken()
        if (token != null) {
            viewModelScope.launch {
                restaurant = Restaurant.read(token)!!
                restaurantName.value= restaurant.name
                restaurantSubscriptionPrice.value = restaurant.subscriptionPrice
                restaurantAddress.value= restaurant.address
                restaurantDeliveryHours.value= restaurant.deliveryHours
                restaurantOpenHours.value= restaurant.openedHours
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
                viewModelScope.launch {
                    restaurant.name = restaurantName
                    restaurant.subscriptionPrice = subscriptionPrice
                    restaurant.address = address
                    restaurant.deliveryHours = deliveryHours
                    restaurant.openedHours = openHours
                    if( restaurant.update())
                        response.value = R.string.personal_data_saved
                    else
                        response.value = R.string.error
                }
            }
        }
    }
}