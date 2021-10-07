package com.example.lunchforyou.utils

import android.util.Log
import com.parse.ParseQuery

import com.parse.ParseObject

const val CLIENT_TABLE = "Client"


class DatabaseManager {

    companion object {
        fun getUser(token: String) {
            val query = ParseQuery.getQuery<ParseObject>(CLIENT_TABLE)
            query.whereEqualTo("token", token)
            query.orderByDescending("token")
            query.findInBackground { objects, e ->
                if (e == null) {
                    if (objects.isEmpty())
                        Log.d(TAG, "No record found")
                    else
                        Log.d(TAG, objects.first().get("token").toString())
                } else {
                    Log.d(TAG, "Error: " + e.message!!)
                }
            }
        }
    }
}