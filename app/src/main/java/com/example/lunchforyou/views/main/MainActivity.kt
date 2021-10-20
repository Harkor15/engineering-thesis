package com.example.lunchforyou.views.main

import com.example.lunchforyou.utils.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lunchforyou.R
import com.example.lunchforyou.database.DatabaseManager
import com.example.lunchforyou.database.IDeleteClient


class MainActivity : AppCompatActivity(), IDeleteClient {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        DatabaseManager.deleteClient("aaaaaaaaaa",this )
        //DatabaseManager.getClient("aaaaaaaaaa")
       /* val firstObject = ParseObject("FirstClass")
        firstObject.put("message","Hey ! First message from android. Parse is now connected")
        firstObject.saveInBackground {
            if (it != null){
                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
            }else{
                Log.d("MainActivity","Object saved.")
            }
        }*/


    }

    override fun userDeleted() {
        Log.d(TAG, "Success")
    }

    override fun error(e: String) {
        Log.d(TAG, e)
    }
}