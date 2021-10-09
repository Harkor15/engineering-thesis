package com.example.lunchforyou.database

data class Client(val id:String ,val name:String, val surname:String,
                  val address:String, val subscribedRestaurantToken:String)

data class Restaurant(val id:String, val name:String, val address: String,
                      val subscriptionPrice:Double, val deviveryHurs:String,
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