package com.example.lunchforyou.views.restaurant.restaurant_history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.database.UserPreference
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch
import java.util.*

class RestaurantHistoryViewModel: ViewModel() {
    var data = MutableLiveData<List<UserPreference>>()
    var date = MutableLiveData<Date>()

    fun init(){
        date.value = Calendar.getInstance().time
        loadData()
    }

    fun setDate(day:Int, month:Int, year:Int){
        val c = Calendar.getInstance()
        c.set(year,month-1,day,0,0)
        date.value = c.time
        loadData()
    }

    private fun loadData(){
        val restaurantId = LocalDataManager().getUserToken()
        if(restaurantId!=null){
            viewModelScope.launch {
                data.value= UserPreference.read(restaurantId, date.value!!)
            }
        }
    }
}