package com.example.lunchforyou.database

import com.parse.ParseObject
import java.util.*

class MenuDay(var po: ParseObject) {
    val id = po.getString(MenuDayTableNamespace.ID)
    val restaurantId = po.getString(MenuDayTableNamespace.RESTAURANT_ID)
    val date = po.getDate(MenuDayTableNamespace.DATE)
    var option1 = po.getString(MenuDayTableNamespace.OPTION_1)
    var option2 = po.getString(MenuDayTableNamespace.OPTION_2)

    suspend fun update():Boolean{
        po.put(MenuDayTableNamespace.OPTION_1, option1!!)
        po.put(MenuDayTableNamespace.OPTION_2, option2!!)
        return DatabaseManager.save(po)
    }

    companion object{
        suspend fun create(
            restaurantId:String, date:Date, option1:String, option2: String
        ):Boolean{
            val po = ParseObject(MenuDayTableNamespace.TABLE_NAME)
            po.put(MenuDayTableNamespace.RESTAURANT_ID, restaurantId)
            po.put(MenuDayTableNamespace.DATE, date)
            po.put(MenuDayTableNamespace.OPTION_1, option1)
            po.put(MenuDayTableNamespace.OPTION_2, option2)
            return DatabaseManager.save(po)
        }

        suspend fun read(day: Date, restaurantId: String):MenuDay?{
            val response = DatabaseManager.readMenuDay(day, restaurantId)
            return if (response != null) {
                MenuDay(response)
            } else {
                null
            }
        }

        suspend fun readAllSinceToday(restaurantId: String):List<MenuDay>?{
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR,0)
            calendar.set(Calendar.MINUTE,0)
            calendar.set(Calendar.SECOND,0)
            calendar.set(Calendar.MILLISECOND,0)
            val response = DatabaseManager.readMenuDaySince(calendar.time,restaurantId)
            return if(response != null && response.isNotEmpty()){
                val result = mutableListOf<MenuDay>()
                response.forEach{
                    result.add(MenuDay(it))
                }
                result
            }else{
                null
            }
        }
    }
}
