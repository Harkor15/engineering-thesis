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

        fun deleteClient(token: String){ //TODO: TEST
            val  parseObject = ParseObject(ClientTableNamespace.TABLE_NAME)
            parseObject.put(ClientTableNamespace.TOKEN, token)
            parseObject.deleteInBackground{e->
                if(e!=null){
                    //ERROR
                    Log.d(TAG,"Delete error")
                }
            }
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

        object RestaurantTableNamespace{
            const val ID = "id"
            const val token = "token"
            const val NAME ="name"
            const val ADDRESS = "address"
            const val SUBSCRIPTION_PRICE="subscriptionPrice"
            const val DELIVERY_HOURS = "deliveryHours"
            const val OPENED_HOURS="openedHours"
        }

        object SubscriptionTableNamespace{
            const val ID="id"
            const val RESTAURANT_ID="restaurantId"
            const val USER_ID="userId"
            const val DAY_OD_BOUGHT="dayOfBought"
            const val BOUGHT_DAYS="boughtDays"
            const val DAYS_LEFT = "daysLeft"
        }

        object MenuDatTableNamepsace{
            const val ID="id"
            const val RESTAURANT_ID="restaurantId"
            const val DAY="day"
            const val DAY_IN_WEEK="dayInWeek"
            const val SOUPS="soups"
            const val MAIN_COURSES="mainCourses"
        }

        object UserPreferenceTableNamespace{
            const val ID ="id"
            const val USER_ID="userId"
            const val MENU_DAY_ID="menuDayId"
            const val PREFERRED_SOUPS="preferredSoups"
            const val PREFERRED_MAIN_COURSES="preferredMainCourses"
            const val STATUS="status"
        }

        object MessageTAbleNamespace{
            const val ID= "id"
            const val CLIENT_ID="clientId"
            const val RESTAURANT_ID="restaurantId"
            const val IS_FROM_CLIENT="isFromClient"
            const val MESSAGE="message"
        }