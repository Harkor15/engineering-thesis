package com.example.lunchforyou.views.client.client_menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.MenuDay
import com.example.lunchforyou.database.UserPreference
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ClientMenuViewModel:ViewModel() {
    private lateinit var selectedDay : Date
    val userId = LocalDataManager().getUserToken()
    val subscribedRestaurantId = LocalDataManager().getSubscribedRestaurantToken()

    val userPreference = MutableLiveData<UserPreference>()
    val menu = MutableLiveData<MenuDay>()
    val info = MutableLiveData<Int>()

    fun init(){
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR,0)
        calendar.set(Calendar.MINUTE,0)
        calendar.set(Calendar.SECOND,0)
        calendar.set(Calendar.MILLISECOND,0)
        selectedDay = calendar.time

        loadMenuDay()

    }

    fun setPreference(option:String, note:String){
        if(userPreference.value!=null){
            userPreference.value!!.preferredOption=option
                viewModelScope.launch {
                    val result= userPreference.value!!.update()
                    if(result)
                        info.value=R.string.saved
                    else
                        info.value = R.string.error
                }
        }else{
            if(!userId.isNullOrBlank()&& !subscribedRestaurantId.isNullOrBlank())
                viewModelScope.launch {
                    val result = UserPreference.create(userId, subscribedRestaurantId, selectedDay, option, "",note)
                    if(result)
                        info.value = R.string.saved
                    else
                        info.value = R.string.error
                }
        }
    }

    private fun loadMenuDay(){
        if(!subscribedRestaurantId.isNullOrBlank()&& !userId.isNullOrBlank()) {
            viewModelScope.launch {
                val restaurantMenus = MenuDay.readAllSinceToday(subscribedRestaurantId)
                val userPreferences = UserPreference.readAllSinceToday(userId)
                if(!restaurantMenus.isNullOrEmpty()) {
                    val sdf = SimpleDateFormat("ddMMyyyy")
                    val dateSearch =sdf.format(selectedDay)
                    restaurantMenus.forEach {
                        if(dateSearch==sdf.format(it.date!!))
                        {
                            menu.value = it
                        }
                    }
                    if(!userPreferences.isNullOrEmpty()){
                        userPreferences.forEach {
                            if(dateSearch==sdf.format(it.date)){
                                userPreference.value=it
                            }
                        }
                    }
                }else{
                    info.value = R.string.menu_is_not_avaliable_yet
                }
            }
        }
    }
}