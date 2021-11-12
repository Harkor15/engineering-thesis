package com.example.lunchforyou.views.restaurant.restaurant_menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.database.MenuDay
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch
import java.util.*

class RestaurantMenuViewModel: ViewModel() {
    val date = MutableLiveData<String>()
    val response = MutableLiveData<Int>()
    val option1 = MutableLiveData<String>()
    val option2 = MutableLiveData<String>()
    private val calendar = Calendar.getInstance()
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
    private var month = calendar.get(Calendar.MONTH)
    private var year = calendar.get(Calendar.YEAR)
    private var currentMenuDay:MenuDay? = null

    fun init(){
        date.value=getDayString()
        loadMenu()
    }

    fun setDate(day:Int, month:Int, year:Int){
        this.day = day
        this.month = month
        this.year = year
        date.value=getDayString()
        loadMenu()
    }

    fun save(option1:String, option2: String){
        if(currentMenuDay!=null){
            val token = LocalDataManager().getUserToken()
            if(token!=null){
                viewModelScope.launch {
                    val result = MenuDay.create(token, getDayString(), option1, option2)
                    if(result)
                        loadMenu()
                }
            }else{
                viewModelScope.launch {
                    currentMenuDay!!.option1 = option1
                    currentMenuDay!!.option2 = option2
                    currentMenuDay!!.update()
                }
            }
        }
    }

    private fun loadMenu(){
        val token = LocalDataManager().getUserToken()
        if(!token.isNullOrBlank()){
            viewModelScope.launch {
                currentMenuDay = MenuDay.read(getDayString(), token)
                if(currentMenuDay!=null) {
                    option1.value = currentMenuDay!!.option1
                    option2.value = currentMenuDay!!.option2
                }
            }
        }

    }

    private fun getDayString():String{
        return "$day-$month-$year"
    }
}