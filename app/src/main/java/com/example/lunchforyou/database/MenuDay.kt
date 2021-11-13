package com.example.lunchforyou.database

import com.parse.ParseObject

class MenuDay(var po: ParseObject) {
    val id = po.getString(MenuDayTableNamespace.ID)
    val restaurantId = po.getString(MenuDayTableNamespace.RESTAURANT_ID)
    val date = po.getString(MenuDayTableNamespace.DATE)
    var option1 = po.getString(MenuDayTableNamespace.OPTION_1)
    var option2 = po.getString(MenuDayTableNamespace.OPTION_2)

    suspend fun update():Boolean{
        po.put(MenuDayTableNamespace.OPTION_1,option1!!)
        po.put(MenuDayTableNamespace.OPTION_2, option2!!)
        return DatabaseManager.save(po)
    }

    companion object{
        suspend fun create(
            restaurantId:String, date:String, option1:String, option2: String
        ):Boolean{
            val po = ParseObject(MenuDayTableNamespace.TABLE_NAME)
            po.put(MenuDayTableNamespace.RESTAURANT_ID, restaurantId)
            po.put(MenuDayTableNamespace.DATE, date)
            po.put(MenuDayTableNamespace.OPTION_1, option1)
            po.put(MenuDayTableNamespace.OPTION_2, option2)
            return DatabaseManager.save(po)
        }

        suspend fun read(day:String, restaurantId: String):MenuDay?{
            val response = DatabaseManager.readMenuDay(day, restaurantId)
            return if (response != null) {
                MenuDay(response)
            } else {
                null
            }
        }
    }
}
