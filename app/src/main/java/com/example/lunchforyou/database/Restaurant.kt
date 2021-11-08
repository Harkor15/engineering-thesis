package com.example.lunchforyou.database

import com.parse.ParseObject

class Restaurant(var parseObject: ParseObject){
    val id = parseObject.getString(RestaurantTableNamespace.ID)
    val token = parseObject.getString(RestaurantTableNamespace.TOKEN)
    var name = parseObject.getString(RestaurantTableNamespace.NAME)
    var address = parseObject.getString(RestaurantTableNamespace.ADDRESS)
    var subscriptionPrice = parseObject.getString(RestaurantTableNamespace.SUBSCRIPTION_PRICE)
    var deliveryHours = parseObject.getString(RestaurantTableNamespace.DELIVERY_HOURS)
    var openedHours = parseObject.getString(RestaurantTableNamespace.OPENED_HOURS)

    suspend fun  update(): Boolean {
        parseObject.put(RestaurantTableNamespace.NAME, name!!)
        parseObject.put(RestaurantTableNamespace.ADDRESS, address!!)
        parseObject.put(RestaurantTableNamespace.SUBSCRIPTION_PRICE,subscriptionPrice!!)
        parseObject.put(RestaurantTableNamespace.DELIVERY_HOURS,deliveryHours!!)
        parseObject.put(RestaurantTableNamespace.OPENED_HOURS,openedHours!!)

        return DatabaseManager.save(parseObject)
    }


    companion object {
        suspend fun read(token: String): Restaurant? {
            val response = DatabaseManager.readRestaurant(token)
            return if (response != null) {
                Restaurant(response)
            } else {
                null
            }
        }

        suspend fun create(token: String):Boolean{
            val createdParseObject = ParseObject(RestaurantTableNamespace.TABLE_NAME)
            createdParseObject.put(RestaurantTableNamespace.TOKEN, token)
            return DatabaseManager.save(createdParseObject)
        }
    }
}