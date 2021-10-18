package com.example.lunchforyou.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lunchforyou.R
import com.example.lunchforyou.database.DatabaseManager


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DatabaseManager.getClient("aaaaaaaaaa")
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
}