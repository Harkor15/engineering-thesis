package com.example.lunchforyou.views.restaurant.restaurant_messages

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.Client
import com.example.lunchforyou.database.Message
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.coroutineContext

class RestaurantMessagesViewModel:ViewModel() {
    private val ldm = LocalDataManager()
    private val restaurantId =  ldm.getUserToken()
    private var clientDetails : List<Client> = arrayListOf()
    private var currentConversation:Int? = null
    val messages = MutableLiveData<List<Message>>()
    var clients = MutableLiveData<List<String>>()
    val info = MutableLiveData<Int>()


    fun init(){
        downloadClients()
    }

    private fun downloadClients() {
        if (!restaurantId.isNullOrBlank()) {
            viewModelScope.launch {
                val response = Client.readClientsOfRestaurant(restaurantId)
                if (!response.isNullOrEmpty()) {
                    clientDetails = response!!
                    var tmp = ArrayList<String>()
                    for( c in clientDetails ){
                        tmp.add(c.name+ " " + c.surname)
                    }
                    clients.value=tmp
                }
            }
        }
    }

    fun showMessages(id:Int){
        currentConversation=id
        val token = clientDetails[id].token
        if(!token.isNullOrBlank())
            downloadMessages(token)
    }

    private fun downloadMessages(clientId: String){
        if(!restaurantId.isNullOrBlank()) {
            viewModelScope.launch {
                val result = Message.readRestaurantMessages(restaurantId, clientId)
                if(result!=null){
                    messages.value= result!!
                }else{
                    messages.value= arrayListOf()
                }
            }
        }
    }

    fun sendMessage(message: String) {
        if(!restaurantId.isNullOrBlank()){
            if(currentConversation!=null && !restaurantId.isNullOrBlank()){
                val token = clientDetails[currentConversation!!].token
                if(!token.isNullOrBlank()) {
                    viewModelScope.launch {
                        val result = Message.create(
                            token,
                            restaurantId,
                            message,
                            false,
                            Calendar.getInstance().time
                        )
                        if(result){
                            downloadMessages(token)
                        }else{
                            info.value= R.string.error
                        }
                    }
                }
            }
        }
    }


}