package com.example.lunchforyou.views.client.client_settings

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.database.Client
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch

class ClientSettingsViewModel:ViewModel() {
    val client = MediatorLiveData<Client>()

    fun init(){
        val clientId = LocalDataManager().getUserToken()
        if(clientId!=null){
            viewModelScope.launch {
                val cl = Client.read(clientId)
                if(cl!=null){
                    client.value = cl!!
                }
            }
        }
    }

    fun setName(name:String){
        client.value?.name =name
    }

}