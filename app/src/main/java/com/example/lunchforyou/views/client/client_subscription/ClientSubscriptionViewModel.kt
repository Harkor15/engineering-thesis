package com.example.lunchforyou.views.client.client_subscription

import android.text.format.DateUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.Restaurant
import com.example.lunchforyou.database.Subscription
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class ClientSubscriptionViewModel: ViewModel() {
    val restaurantName = MutableLiveData<String>()
    val subscriptionActive = MutableLiveData<Int>()
    val subscriptionUntil = MutableLiveData<String>()
    val subscriptionSince = MutableLiveData<String>()
    val toast = MutableLiveData<Int>()
    private val today = Calendar.getInstance().time
    var subscription: Subscription? = null

    fun init(){
        viewModelScope.launch {
            loadSubscription()
        }
    }

    private suspend fun loadSubscription(){
        val userId= LocalDataManager().getUserToken()
        if(userId!=null) {
            subscription = Subscription.readClientSubscription(userId)
            if(subscription!=null){
                val restaurant = Restaurant.read(subscription!!.restaurantId!!)
                if(restaurant!=null){
                    restaurantName.value=restaurant.name
                    subscriptionActive.value=if (subscription!!.lastDayOfSub!!<= today){
                        R.string.yes
                    }else{
                        R.string.no
                    }
                    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                    subscriptionUntil.value = dateFormat.format( subscription!!.lastDayOfSub!!)
                    subscriptionSince.value = dateFormat.format( subscription!!.dayOfBought!!)
                }
            }
        }
    }

    fun extendSub(){
        if(subscription==null) {
            val restaurantId = LocalDataManager().getSubscribedRestaurantToken()
            val userId = LocalDataManager().getUserToken()
            val cal = Calendar.getInstance()
            cal.add(Calendar.MONTH, 1)
            if (restaurantId != null && userId != null) {
                viewModelScope.launch {
                    val result = Subscription.create(restaurantId, userId, today, cal.time)
                    if(result){
                        loadSubscription()
                    }
                }
            }
        }else{
            val c = Calendar.getInstance()
            if(subscription!!.lastDayOfSub!! >=today)
                c.time = subscription!!.lastDayOfSub!!
            c.add(Calendar.MONTH, 1)
            subscription!!.lastDayOfSub=c.time
            viewModelScope.launch {
                if(subscription!!.update())
                    loadSubscription()
            }
        }
    }

}