package com.example.lunchforyou.views.client.client_settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.Client
import com.example.lunchforyou.utils.LocalDataManager
import kotlinx.coroutines.launch

class ClientSettingsViewModel:ViewModel() {
    val client = MutableLiveData<Client>()
    val response = MutableLiveData<Int>()

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

    fun setDetails(name:String, surname:String, address:String){
        if(name.isNotBlank() && surname.isNotBlank() && address.isNotBlank()){
            client.value?.name =name
            client.value?.surname =surname
            client.value?.address =address
            viewModelScope.launch {
                val result = client.value?.update()
                if(result!=null && result){
                    response.value=R.string.personal_data_saved
                }
            }
        }else{
            response.value= R.string.fill_in_the_balnks
        }
    }



}