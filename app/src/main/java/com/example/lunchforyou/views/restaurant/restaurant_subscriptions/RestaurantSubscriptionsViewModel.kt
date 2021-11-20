package com.example.lunchforyou.views.restaurant.restaurant_subscriptions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.database.Subscription
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch
import java.util.*

class RestaurantSubscriptionsViewModel: ViewModel() {
    val activeSubscribers = MutableLiveData<Int>()
    val newSubscribers = MutableLiveData<Int>()
    val lostSubscribers = MutableLiveData<Int>()
    val allSubscribers = MutableLiveData<Int>()
    val subscribers = MutableLiveData<List<Subscription>>()


    fun init(){
        val restaurantId = LocalDataManager().getUserToken()
        if(restaurantId!=null) {
            viewModelScope.launch {
                val result = Subscription.readRestaurantSubscriptions(restaurantId)
                if(result!=null){
                    subscribers.value= result!!
                    showData()
                }
            }
        }
    }

    private fun showData(){
        if(subscribers.value!=null) {
            var activeS = 0
            var newS = 0
            var lostS = 0
            val calendar = Calendar.getInstance()
            val today = calendar.time
            calendar.set(Calendar.DAY_OF_MONTH,1)
            calendar.set(Calendar.HOUR,0)
            calendar.set(Calendar.MINUTE,0)
            calendar.set(Calendar.MILLISECOND,0)
            val firstDayOfThisMonth = calendar.time
            allSubscribers.value = subscribers.value!!.size
            subscribers.value!!.forEach{
                if(it.lastDayOfSub!=null) {
                    if (it.lastDayOfSub!! > today)
                        activeS++
                    if(it.lastDayOfSub!!>firstDayOfThisMonth && it.lastDayOfSub!!<today)
                        lostS++
                }
                if(it.dayOfBought!=null)
                    if(it.dayOfBought!!>firstDayOfThisMonth)
                        newS++
                activeSubscribers.value=activeS
                lostSubscribers.value=lostS
                newSubscribers.value=newS
            }
        }
    }
}