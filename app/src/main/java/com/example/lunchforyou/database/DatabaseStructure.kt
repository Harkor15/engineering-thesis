package com.example.lunchforyou.database

import com.parse.ParseObject

data class Client(val id:String ,val token:String , val name:String, val surname:String,
                  val address:String, val subscribedRestaurantToken:String){
    constructor(parseObject: ParseObject) : this(
        parseObject.getString(ClientTableNamespace.ID)!!,
        parseObject.getString(ClientTableNamespace.TOKEN)!!,
        parseObject.getString(ClientTableNamespace.NAME)!!,
        parseObject.getString(ClientTableNamespace.SURNAME)!!,
        parseObject.getString(ClientTableNamespace.ADDRESS)!!,
        parseObject.getString(ClientTableNamespace.SUBSCRIBED_RESTAURANT_TOKEN)!!,
    )
}

/*class Client2(parseObject: ParseObject){
    val id = parseObject.getString(ClientTableNamespace.ID)!!
val token:String , val name:String, val surname:String,
val address:String, val subscribedRestaurantToken:String

}*/

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
    const val token = "token"
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