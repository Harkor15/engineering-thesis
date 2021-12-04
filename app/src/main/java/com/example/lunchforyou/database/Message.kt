package com.example.lunchforyou.database

import com.parse.ParseObject
import java.util.*

class Message(var parseObject: ParseObject) {
    var clientId = parseObject.getString(MessageTableNamespace.CLIENT_ID)
    var restaurantId = parseObject.getString(MessageTableNamespace.RESTAURANT_ID)
    var isFromClient = parseObject.getBoolean(MessageTableNamespace.IS_FROM_CLIENT)
    var message = parseObject.getString(MessageTableNamespace.MESSAGE)
    var date = parseObject.getDate(MessageTableNamespace.DATE)

    suspend fun update():Boolean{
        parseObject.put(MessageTableNamespace.CLIENT_ID,clientId!!)
        parseObject.put(MessageTableNamespace.RESTAURANT_ID,restaurantId!!)
        parseObject.put(MessageTableNamespace.IS_FROM_CLIENT,isFromClient)
        parseObject.put(MessageTableNamespace.MESSAGE,message!!)
        parseObject.put(MessageTableNamespace.DATE,date!!)
        return DatabaseManager.save(parseObject)
    }


    companion object{
        suspend fun create(
            clientId:String,
            restaurantId:String,
            message:String,
            isFromClient:Boolean,
            date: Date
        ):Boolean{
            val po = ParseObject(MessageTableNamespace.TABLE_NAME)
            po.put(MessageTableNamespace.CLIENT_ID,clientId)
            po.put(MessageTableNamespace.RESTAURANT_ID,restaurantId)
            po.put(MessageTableNamespace.IS_FROM_CLIENT,isFromClient)
            po.put(MessageTableNamespace.MESSAGE,message)
            po.put(MessageTableNamespace.DATE,date)
            return DatabaseManager.save(po)
        }

        suspend fun readClientMessages(clientId:String):List<Message>?{
            val response = DatabaseManager.readClientMessages(clientId)
            return if(response!=null && response.isNotEmpty()){
                val result = mutableListOf<Message>()
                response.forEach {
                    result.add(Message(it))
                }
                result
            }else{
                null
            }
        }

        suspend fun readRestaurantMessages(restaurantId:String, clientId: String):List<Message>?{
            val response = DatabaseManager.readAllRestaurantMessages(restaurantId, clientId)
            return if(response!=null && response.isNotEmpty()){
                val result = mutableListOf<Message>()
                response.forEach {
                    result.add(Message(it))
                }
                result
            }else{
                null
            }
        }
    }
}