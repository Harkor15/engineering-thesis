package com.example.lunchforyou.database

import com.parse.ParseObject

class Client(var parseObject: ParseObject) {
    var id = parseObject.getString(ClientTableNamespace.ID)
    var token = parseObject.getString(ClientTableNamespace.TOKEN)
    var name = parseObject.getString(ClientTableNamespace.NAME)
    var surname = parseObject.getString(ClientTableNamespace.SURNAME)
    var address = parseObject.getString(ClientTableNamespace.ADDRESS)
    var subscribedRestaurantToken =
        parseObject.getString(ClientTableNamespace.SUBSCRIBED_RESTAURANT_TOKEN)

    private fun reInit(parseObject: ParseObject) {
        this.parseObject = parseObject
        id = parseObject.getString(ClientTableNamespace.ID)
        token = parseObject.getString(ClientTableNamespace.TOKEN)
        name = parseObject.getString(ClientTableNamespace.NAME)
        surname = parseObject.getString(ClientTableNamespace.SURNAME)
        address = parseObject.getString(ClientTableNamespace.ADDRESS)
        subscribedRestaurantToken =
            parseObject.getString(ClientTableNamespace.SUBSCRIBED_RESTAURANT_TOKEN)
    }

    suspend fun Update(): Boolean {
        parseObject.put(ClientTableNamespace.TOKEN, token!!)
        parseObject.put(ClientTableNamespace.NAME, name!!)
        parseObject.put(ClientTableNamespace.SURNAME, surname!!)
        parseObject.put(ClientTableNamespace.ADDRESS, address!!)
        parseObject.put(
            ClientTableNamespace.SUBSCRIBED_RESTAURANT_TOKEN,
            subscribedRestaurantToken!!
        )
        return DatabaseManager.save(parseObject)
    }

    suspend fun Delete(): Boolean {
        return DatabaseManager.deleteClient(parseObject)
    }

    suspend fun Create(
        token: String,
        name: String,
        surname: String,
        address: String,
        subscribedRestaurantToken: String?
    ) {
        var createdParseObject = ParseObject(ClientTableNamespace.TABLE_NAME)
        createdParseObject.put(ClientTableNamespace.TOKEN, token)
        createdParseObject.put(ClientTableNamespace.NAME, name)
        createdParseObject.put(ClientTableNamespace.SURNAME, surname)
        createdParseObject.put(ClientTableNamespace.ADDRESS, address)
        if (subscribedRestaurantToken != null)
            createdParseObject.put(
                ClientTableNamespace.SUBSCRIBED_RESTAURANT_TOKEN,
                subscribedRestaurantToken
            )
        DatabaseManager.save(createdParseObject)
    }

    companion object {
        suspend fun Read(token: String): Client? {
            val response = DatabaseManager.readClient(token)
            return if (response != null) {
                Client(response)
            } else {
                null
            }
        }
    }



}


data class Restaurant(val id:String, val name:String, val address: String,
                      val subscriptionPrice:Double, val deliveryHours:String,
                      val openedHours:String)

data class Subscription(val id:String,val restaurantId:String, val userId:String,
                        val boughtDate:String, val boughtDays:Int, val daysLeft:Int)

data class MenuDay(val id:String, val restaurantId: String, val day:String, val dayInWeek:Int,
                   val soups:List<String>, val mainCourses:List<String>)

data class UserPreference(val id:String, val userId: String, val menuDayId:String,
                          val preferredSoups:List<Int>, val preferredMainCourses:List<Int>,
                          val status:String)

data class Message(val id:String, val clientId:String, val restaurantId:String,
                   val isFromClient:Boolean, val message:String )


object ClientTableNamespace{
    const val TABLE_NAME = "Client"
    const val ID = "id"
    const val TOKEN = "token"
    const val NAME = "name"
    const val SURNAME = "surname"
    const val ADDRESS = "address"
    const val SUBSCRIBED_RESTAURANT_TOKEN = "subscribed_restaurant_token"
}

object RestaurantTableNamespace{
    const val TABLE_NAME = "Restaurant"
    const val ID = "id"
    const val TOKEN = "token"
    const val NAME ="name"
    const val ADDRESS = "address"
    const val SUBSCRIPTION_PRICE="subscriptionPrice"
    const val DELIVERY_HOURS = "deliveryHours"
    const val OPENED_HOURS="openedHours"
}

object SubscriptionTableNamespace{
    const val TABLE_NAME = "Subscription"
    const val ID="id"
    const val RESTAURANT_ID="restaurantId"
    const val USER_ID="userId"
    const val DAY_OD_BOUGHT="dayOfBought"
    const val BOUGHT_DAYS="boughtDays"
    const val DAYS_LEFT = "daysLeft"
}

object MenuDayTableNamespace{
    const val TABLE_NAME = "MenuDay"
    const val ID="id"
    const val RESTAURANT_ID="restaurantId"
    const val DAY="day"
    const val DAY_IN_WEEK="dayInWeek"
    const val SOUPS="soups"
    const val MAIN_COURSES="mainCourses"
}

object UserPreferenceTableNamespace{
    const val TABLE_NAME = "UserPreference"
    const val ID ="id"
    const val USER_ID="userId"
    const val MENU_DAY_ID="menuDayId"
    const val PREFERRED_SOUPS="preferredSoups"
    const val PREFERRED_MAIN_COURSES="preferredMainCourses"
    const val STATUS="status"
}

object MessageTAbleNamespace{
    const val TABLE_NAME = "Message"
    const val ID= "id"
    const val CLIENT_ID="clientId"
    const val RESTAURANT_ID="restaurantId"
    const val IS_FROM_CLIENT="isFromClient"
    const val MESSAGE="message"
}