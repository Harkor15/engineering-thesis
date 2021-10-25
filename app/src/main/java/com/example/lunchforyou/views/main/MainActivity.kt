package com.example.lunchforyou.views.main

import com.example.lunchforyou.utils.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lunchforyou.R
import com.example.lunchforyou.database.*
import com.parse.ParseObject


class MainActivity : AppCompatActivity(), DatabaseResponseInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    override fun success(table: String?, operation: DatabaseOperationType?) {
        Log.d(TAG,"Success")
    }

    override fun error(e: String, table: String?, operation: DatabaseOperationType?) {
        Log.d(TAG, "ERROR $e")
    }

    override fun readed(parseObject: ParseObject) {
        val client = Client2(parseObject)
        Log.d(TAG,"Readed: ${client.token} name: ${client.name} ")
    }
}