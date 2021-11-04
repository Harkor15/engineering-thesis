package com.example.lunchforyou.views.main

import android.content.Context
import com.example.lunchforyou.utils.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.lunchforyou.R
import com.example.lunchforyou.database.*
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contextOfApplication = applicationContext
    }

    override fun onBackPressed() {
    }

    companion object{
        lateinit var contextOfApplication: Context
    }

}