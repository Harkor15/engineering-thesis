package com.example.lunchforyou.database

import com.parse.ParseObject

class MenuDay(var po: ParseObject) {
    val id = po.getString(MenuDayTableNamespace.ID)
    val restaurantId = po.getString(MenuDayTableNamespace.RESTAURANT_ID)
    val day = po.getString(MenuDayTableNamespace.DAY)
    var soups = po.getList<String>(MenuDayTableNamespace.SOUPS)
    var mainCourses = po.getList<String>(MenuDayTableNamespace.MAIN_COURSES)

    suspend fun update():Boolean{
        po.put(MenuDayTableNamespace.SOUPS, soups!!)
        po.put(MenuDayTableNamespace.MAIN_COURSES, mainCourses!!)
        return DatabaseManager.save(po)
    }

    companion object{
        suspend fun create(
            restaurantId:String, day:String, soups:List<String>, mainCourses:List<String>
        ):Boolean{
            val po = ParseObject(MenuDayTableNamespace.TABLE_NAME)
            po.put(MenuDayTableNamespace.RESTAURANT_ID, restaurantId)
            po.put(MenuDayTableNamespace.DAY, day)
            po.put(MenuDayTableNamespace.SOUPS, soups)
            po.put(MenuDayTableNamespace.MAIN_COURSES, mainCourses)
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
