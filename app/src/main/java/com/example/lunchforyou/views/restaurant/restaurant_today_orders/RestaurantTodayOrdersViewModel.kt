package com.example.lunchforyou.views.restaurant.restaurant_today_orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.database.UserPreference
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch
import java.util.*

class RestaurantTodayOrdersViewModel: ViewModel() {
    var data = MutableLiveData<List<UserPreference>>()

    fun init(){
        val restaurantId = LocalDataManager().getUserToken()
        if(restaurantId!=null){
            viewModelScope.launch {
                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR, 0)
                cal.set(Calendar.MINUTE, 0)
                cal.set(Calendar.SECOND, 0)
                cal.set(Calendar.MILLISECOND, 0)
                val preferences =UserPreference.read(restaurantId, cal.time)
                if(preferences!=null)
                    data.value=preferences!!
            }
        }
    }


}