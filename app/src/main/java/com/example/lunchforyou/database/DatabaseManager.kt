        package com.example.lunchforyou.database

import android.util.Log
import com.example.lunchforyou.utils.TAG
import com.parse.ParseQuery

import com.parse.ParseObject




class DatabaseManager {

    companion object {
        fun getUser(token: String) {
            val query = ParseQuery.getQuery<ParseObject>(CLIENT_TABLE)
            query.whereEqualTo(TOKEN_FIELD, token)
            query.orderByDescending(TOKEN_FIELD)
            query.findInBackground { objects, e ->
                if (e == null) {
                    if (objects.isEmpty())
                        Log.d(TAG, "No record found")
                    else
                        Log.d(TAG, objects.first().get(TOKEN_FIELD).toString())
                } else {
                    Log.d(TAG, "Error: " + e.message!!)
                }
            }
        }

        fun deleteUser(id: String){}

        fun createRestaurant(){}

        fun createNewUser(){

        }

        fun getRestaurant(){

        }

        fun getRestaurantSubscribers(id: String){

        }

        fun getRestaurantUsersPreferences(id:String, day:String){

        }

        fun setOrderStatus(){

        }

        fun setRestaurantDetails(){

        }

        fun getMenu(restaurantId: String, day: String){

        }

        fun setMenu(restaruantId: String, day:String){

        }


        private const val CLIENT_TABLE = "Client"
        private const val TOKEN_FIELD = "Token"
    }
}