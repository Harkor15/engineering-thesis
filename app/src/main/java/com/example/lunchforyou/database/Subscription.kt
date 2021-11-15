package com.example.lunchforyou.database

import com.parse.ParseObject
import java.util.*

class Subscription(var parseObject:ParseObject) {
    var id = parseObject.getString(SubscriptionTableNamespace.ID)
    var restaurantId = parseObject.getString(SubscriptionTableNamespace.RESTAURANT_ID)
    var userId = parseObject.getString(SubscriptionTableNamespace.USER_ID)
    var dayOfBought = parseObject.getDate(SubscriptionTableNamespace.DAY_OF_BOUGHT)
    var lastDayOfSub = parseObject.getDate(SubscriptionTableNamespace.LAST_DAY_OF_SUBSCRIPTION)

    suspend fun update():Boolean{
        parseObject.put(SubscriptionTableNamespace.RESTAURANT_ID, restaurantId!!)
        parseObject.put(SubscriptionTableNamespace.USER_ID, userId!!)
        parseObject.put(SubscriptionTableNamespace.DAY_OF_BOUGHT, dayOfBought!!)
        parseObject.put(SubscriptionTableNamespace.LAST_DAY_OF_SUBSCRIPTION, lastDayOfSub!!)
        return DatabaseManager.save(parseObject)
    }

    companion object{
        suspend fun create(
            restaurantId:String,
            userId:String,
            dayOfBought: Date,
            lastDayOfSub: Date
        ):Boolean{
            val createdParseObject = ParseObject(SubscriptionTableNamespace.TABLE_NAME)
            createdParseObject.put(SubscriptionTableNamespace.RESTAURANT_ID, restaurantId)
            createdParseObject.put(SubscriptionTableNamespace.USER_ID, userId)
            createdParseObject.put(SubscriptionTableNamespace.DAY_OF_BOUGHT, dayOfBought)
            createdParseObject.put(SubscriptionTableNamespace.LAST_DAY_OF_SUBSCRIPTION, lastDayOfSub)
            return DatabaseManager.save(createdParseObject)
        }

        suspend fun readRestaurantSubscriptions(restaurantId: String):List<Subscription>?{
            val response = DatabaseManager.readRestaurantSubscriptions(restaurantId)
            return if(response!=null&& response.isNotEmpty()){
                val result = mutableListOf<Subscription>()
                response.forEach{
                    result.add(Subscription(it))
                }
                result
            }else{
                null
            }
        }

        suspend fun readClientSubscription(clientId:String):Subscription?{
            val response = DatabaseManager.readClientSubscription(clientId)
            return if (response != null) {
                Subscription(response)
            } else {
                null
            }
        }
    }
}