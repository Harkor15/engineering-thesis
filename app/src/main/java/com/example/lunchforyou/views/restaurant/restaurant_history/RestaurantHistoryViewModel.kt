package com.example.lunchforyou.views.restaurant.restaurant_history

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.database.UserPreference
import com.example.lunchforyou.utils.LocalDataManager
import com.example.lunchforyou.utils.TAG
import kotlinx.coroutines.launch
import java.util.*

class RestaurantHistoryViewModel: ViewModel() {
    var data = MutableLiveData<List<UserPreference>>()
    var date = MutableLiveData<Date>()

    fun init(){
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        date.value = cal.time
        loadData()
    }

    fun setDate(day:Int, month:Int, year:Int){
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.DAY_OF_MONTH, day)
        cal.set(Calendar.HOUR, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        date.value = cal.time
        loadData()
    }

    private fun loadData(){
        val restaurantId = LocalDataManager().getUserToken()
        if(restaurantId!=null){
            viewModelScope.launch {
                val preferences = UserPreference.read(restaurantId, date.value!!)
                if(preferences!=null) {
                    data.value = preferences!!
                    Log.d(TAG,"preferences size: ${preferences.size}" )
                }
            }
        }else{
            data.value = listOf<UserPreference>()
        }
    }
}