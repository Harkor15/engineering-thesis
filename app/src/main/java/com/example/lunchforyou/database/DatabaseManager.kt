        package com.example.lunchforyou.database

import android.util.Log
import com.example.lunchforyou.utils.TAG
import com.parse.ParseQuery

import com.parse.ParseObject




class DatabaseManager {

    companion object {
        fun getClient(token: String, response: iGetClient ) {
            val query = ParseQuery.getQuery<ParseObject>(ClientTableNamespace.TABLE_NAME)
            query.whereEqualTo(ClientTableNamespace.TOKEN, token)
            query.orderByDescending(ClientTableNamespace.TOKEN)
            query.findInBackground { objects, e ->
                if (e == null) {
                    if (objects.isEmpty())
                        response.error("No record found")
                    else
                        response.getClientResponse(Client(objects.first()))
                } else {
                    response.error(e.message!!)
                }
            }
        }

        fun deleteUser(id: String){

        }

        fun createRestaurant(){
            val entity = ParseObject(RESTAURANT_TABLE)
        }

        fun createNewUser(){
            val entity = ParseObject(ClientTableNamespace.TABLE_NAME)
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

        fun setMenu(restaurantId: String, day:String){

        }



        private const val RESTAURANT_TABLE = "Restaurant"
    }
}
        object ClientTableNamespace{
            const val TABLE_NAME = "client"
            const val ID = "id"
            const val TOKEN = "token"
            const val NAME = "name"
            const val SURNAME = "surname"
            const val ADDRESS = "address"
            const val SUBSCRIBED_RESTAURANT_TOKEN = "subscribed_restaurant_token"
        }