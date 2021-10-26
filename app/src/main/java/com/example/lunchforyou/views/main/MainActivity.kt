package com.example.lunchforyou.views.main

import com.example.lunchforyou.utils.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.*
import com.parse.ParseObject
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycleScope.launch {
            val cli = Client2.Read("aaaaa")
            if(cli!=null){
                Log.d(TAG,cli.name!!);
            }
        }
        //Client2.Create("aaaaa","Name","Surname", "Address",null, this)
        //Client2.Read("aaaaa",this)


        //DatabaseManager.deleteClient("aaaaaaaaaa",null )
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


}