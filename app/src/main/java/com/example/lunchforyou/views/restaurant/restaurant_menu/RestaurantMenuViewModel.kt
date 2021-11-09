package com.example.lunchforyou.views.restaurant.restaurant_menu

import androidx.lifecycle.MutableLiveData
import java.util.*

class RestaurantMenuViewModel {
    val date = MutableLiveData<String>()
    val response = MutableLiveData<Int>()

    fun init(){
        val calendar = Calendar.getInstance()
        date.value="${calendar.get(Calendar.DAY_OF_MONTH)}-${calendar.get(Calendar.MONTH)
        }-${calendar.get(Calendar.YEAR)}"
    }
    fun setDate(day:Int, month:Int, year:Int){
        date.value="$day-$month-$year"
    }

    fun save(date:String, option1:String, option2: String){
        //TODO: SAVE
    }
}