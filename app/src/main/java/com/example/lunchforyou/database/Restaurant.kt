package com.example.lunchforyou.database

import com.parse.ParseObject

class Restaurant(var parseObject: ParseObject){
    var id = parseObject.getString(RestaurantTableNamespace.ID)
    var token = parseObject.getString(RestaurantTableNamespace.TOKEN)
    var name = parseObject.getString(RestaurantTableNamespace.NAME)
    val address = parseObject.getString(RestaurantTableNamespace.ADDRESS)
    val subscriptionPrice = parseObject.getString(RestaurantTableNamespace.SUBSCRIPTION_PRICE)
    val deliveryHours = parseObject.getString(RestaurantTableNamespace.DELIVERY_HOURS)
    val openedHours = parseObject.getString(RestaurantTableNamespace.OPENED_HOURS)

    companion object {
        suspend fun Read(token: String): Restaurant? {
            val response = DatabaseManager.readRestaurant(token)
            return if (response != null) {
                Restaurant(response)
            } else {
                null
            }
        }
    }
}