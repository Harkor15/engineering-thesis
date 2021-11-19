package com.example.lunchforyou.views.restaurant.restaurant_menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.MenuDay
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch
import java.util.*

class RestaurantMenuViewModel: ViewModel() {
    val date = MutableLiveData<Date>()
    val info= MutableLiveData<Int>()
    val option1 = MutableLiveData<String>()
    val option2 = MutableLiveData<String>()
    private var currentMenuDay:MenuDay? = null

    fun init(){
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR,0)
        calendar.set(Calendar.MINUTE,0)
        calendar.set(Calendar.SECOND,0)
        calendar.set(Calendar.MILLISECOND,0)
        date.value = calendar.time
        loadMenu()
    }

    fun setDate(day:Int, month:Int, year:Int){
        val cal = Calendar.getInstance()
        cal.time = date.value!!
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.DAY_OF_MONTH, day)
        cal.set(Calendar.HOUR, 0)
        cal.set(Calendar.MILLISECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        date.value=cal.time
        loadMenu()
    }

    fun save(option1:String, option2: String){
        if(currentMenuDay==null) {
            val token = LocalDataManager().getUserToken()
            if (token != null) {
                viewModelScope.launch {
                    val result = MenuDay.create(token, date.value!!, option1, option2)
                    if (result) {
                        loadMenu()
                        info.value = R.string.saved
                    } else
                        info.value = R.string.error
                }
            }
        }
        else{
            viewModelScope.launch {
                currentMenuDay!!.option1 = option1
                currentMenuDay!!.option2 = option2
                if (currentMenuDay!!.update())
                    info.value = R.string.saved
                else
                    info.value = R.string.error
            }
        }
    }

    private fun loadMenu(){
        val token = LocalDataManager().getUserToken()
        if(!token.isNullOrBlank()){
            viewModelScope.launch {
                currentMenuDay = MenuDay.read(date.value!!, token)
                if(currentMenuDay!=null) {
                    option1.value = currentMenuDay!!.option1
                    option2.value = currentMenuDay!!.option2
                }
            }
        }

    }
}