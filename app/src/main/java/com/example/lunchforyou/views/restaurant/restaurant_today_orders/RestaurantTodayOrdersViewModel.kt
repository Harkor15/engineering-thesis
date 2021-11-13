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
                data.value=UserPreference.read(restaurantId, getTodayString())
            }
        }
    }

    private fun getTodayString():String{
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        return "$day.$month.$year"
    }

}