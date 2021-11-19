        package com.example.lunchforyou.database

import android.util.Log
import com.example.lunchforyou.utils.TAG
import com.parse.ParseQuery

import com.parse.ParseObject
import java.lang.Exception
import java.util.*


        class DatabaseManager {

    companion object {

        suspend fun save(parseObject: ParseObject):Boolean{
            return try {
                parseObject.save()
                true
            }catch (e:Exception){
                Log.d(TAG,e.message!!)
                false
            }
        }

        suspend fun readClient(token: String ):ParseObject? {
            val query = ParseQuery.getQuery<ParseObject>(ClientTableNamespace.TABLE_NAME)
            query.whereEqualTo(ClientTableNamespace.TOKEN, token)
            query.orderByDescending(ClientTableNamespace.TOKEN)
            return try {
                val objects = query.find()
                if (objects.isEmpty())
                    null
                else
                    (objects.first())
            }catch (e:Exception){
                Log.d(TAG,e.message!!)
                null
            }
        }

        fun readRestaurant(token: String):ParseObject?{
            val query = ParseQuery.getQuery<ParseObject>(RestaurantTableNamespace.TABLE_NAME)
            query.whereEqualTo(RestaurantTableNamespace.TOKEN, token)
            query.orderByDescending(RestaurantTableNamespace.TOKEN)
            return try {
                val objects = query.find()
                if (objects.isEmpty())
                    null
                else
                    (objects.first())
            }catch (e:Exception){
                Log.d(TAG,e.message!!)
                null
            }
        }

        fun readMenuDay(date: Date, restaurantId: String):ParseObject?{
            val query = ParseQuery.getQuery<ParseObject>(MenuDayTableNamespace.TABLE_NAME)
            query.whereEqualTo(MenuDayTableNamespace.RESTAURANT_ID, restaurantId)
                .whereEqualTo(MenuDayTableNamespace.DATE, date  )
            return try {
                val objects = query.find()
                if (objects.isEmpty())
                    null
                else
                    (objects.first())
            }catch (e:Exception){
                Log.d(TAG,e.message!!)
                null
            }
        }

        fun readMenuDaySince(date: Date, restaurantId: String):List<ParseObject>?{
            val query = ParseQuery.getQuery<ParseObject>(MenuDayTableNamespace.TABLE_NAME)
            query.whereEqualTo(MenuDayTableNamespace.RESTAURANT_ID, restaurantId)
                .whereGreaterThanOrEqualTo(MenuDayTableNamespace.DATE, date)
            return try {
                val objects = query.find()
                if (objects.isEmpty())
                    null
                else
                    objects
            }catch (e:Exception){
                Log.d(TAG,e.message!!)
                null
            }
        }

        fun readUserPreference(userId: String): List<ParseObject>? {
            val query = ParseQuery<ParseObject>(UserPreferenceTableNamespace.TABLE_NAME)
            query.whereEqualTo(UserPreferenceTableNamespace.USER_ID, userId)
            return try{
                val objects = query.find()
                if(objects.isEmpty())
                    null
                else
                    objects
            }catch (e:Exception){
                Log.d(TAG, e.message!!)
                null
            }
        }

        fun readUserPreference(restaurantId: String, date: Date): List<ParseObject>? {
            val query = ParseQuery<ParseObject>(UserPreferenceTableNamespace.TABLE_NAME)
            query.whereEqualTo(UserPreferenceTableNamespace.SUBSCRIBED_RESTAURANT, restaurantId)
                .whereEqualTo(UserPreferenceTableNamespace.DATE, date)
            return try{
                val objects = query.find()
                if(objects.isEmpty())
                    null
                else
                    objects
            }catch (e:Exception){
                Log.d(TAG, e.message!!)
                null
            }
        }

        fun readUserPreferenceSince(time: Date, userId: String): List<ParseObject>? {
            val query = ParseQuery<ParseObject>(UserPreferenceTableNamespace.TABLE_NAME)
            query.whereEqualTo(UserPreferenceTableNamespace.USER_ID, userId)
                .whereGreaterThanOrEqualTo(UserPreferenceTableNamespace.DATE, time)
            return try{
                val objects = query.find()
                if(objects.isEmpty())
                    null
                else
                    objects
            }catch (e:Exception){
                Log.d(TAG, e.message!!)
                null
            }
        }


        fun readClientSubscription(userId:String):ParseObject?{
            val query = ParseQuery.getQuery<ParseObject>(SubscriptionTableNamespace.TABLE_NAME)
            query.whereEqualTo(SubscriptionTableNamespace.USER_ID, userId)
            return try {
                val objects = query.find()
                if (objects.isEmpty())
                    null
                else
                    (objects.first())
            }catch (e:Exception){
                Log.d(TAG,e.message!!)
                null
            }
        }

        fun readRestaurantSubscriptions(restaurantId:String):List<ParseObject>?{
            val query = ParseQuery.getQuery<ParseObject>(SubscriptionTableNamespace.TABLE_NAME)
            query.whereEqualTo(SubscriptionTableNamespace.RESTAURANT_ID, restaurantId)
            return try {
                val objects = query.find()
                if (objects.isEmpty())
                    null
                else
                    objects
            }catch (e:Exception){
                Log.d(TAG,e.message!!)
                null
            }
        }

        fun deleteClient(parseObject: ParseObject):Boolean{
            return try {
                parseObject.delete()
                true
            }catch (e:Exception){
                Log.d(TAG,e.message!!)
                false
            }
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
