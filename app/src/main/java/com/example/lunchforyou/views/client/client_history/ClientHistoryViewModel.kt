package com.example.lunchforyou.views.client.client_history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.database.UserPreference
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch
import java.util.*

class ClientHistoryViewModel: ViewModel() {
    private lateinit var today : Date
    val userPreferences = MutableLiveData<List<UserPreference>>()


    fun init(){
        setCalendar()
        loadData()
    }

    private fun loadData(){
        val userId = LocalDataManager().getUserToken()
        if(userId!=null) {
            viewModelScope.launch {
                val result =UserPreference.read(userId)
                if(result!=null){
                    userPreferences.value=result!!
                }
            }
        }
    }

    private fun setCalendar(){
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR,0)
        calendar.set(Calendar.MINUTE,0)
        calendar.set(Calendar.SECOND,0)
        calendar.set(Calendar.MILLISECOND,0)
        today = calendar.time
    }
}