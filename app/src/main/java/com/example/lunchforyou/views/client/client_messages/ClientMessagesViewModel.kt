package com.example.lunchforyou.views.client.client_messages

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.Message
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch
import java.util.*

class ClientMessagesViewModel: ViewModel() {
    private val ldm = LocalDataManager()
    private val userId = ldm.getUserToken()
    private val subscribedRestaurant  = ldm.getSubscribedRestaurantToken()

    val messages = MutableLiveData<List<Message>>()
    val info = MutableLiveData<Int>()

    fun init(){
        if(userId != null) {
            viewModelScope.launch {
                val result = Message.readClientMessages(userId)
                if(result!=null){
                    messages.value= result!!
                }
            }
        }
    }

    fun sendMessage(message:String){
        if(!userId.isNullOrBlank() && !subscribedRestaurant.isNullOrBlank()) {
            viewModelScope.launch {
                val res = Message.create(userId, subscribedRestaurant, message, true, Calendar.getInstance().time)
                if (res){
                    info.value= R.string.success
                }else{
                    info.value=R.string.error
                }
            }
        }
    }
}