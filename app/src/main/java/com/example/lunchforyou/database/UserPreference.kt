package com.example.lunchforyou.database

import com.parse.ParseObject

class UserPreference(var parseObject: ParseObject) {
    var id = parseObject.getString(UserPreferenceTableNamespace.ID)
    var userId = parseObject.getString(UserPreferenceTableNamespace.USER_ID)
    var date = parseObject.getString(UserPreferenceTableNamespace.DATE)
    var preferredOption = parseObject.getString(UserPreferenceTableNamespace.PREFERRED_OPTION)
    var status = parseObject.getString(UserPreferenceTableNamespace.STATUS)
    var note = parseObject.getString(UserPreferenceTableNamespace.NOTE)
    var address = parseObject.getString(UserPreferenceTableNamespace.ADDRESS)

    suspend fun update():Boolean{
        parseObject.put(UserPreferenceTableNamespace.USER_ID, userId!!)
        parseObject.put(UserPreferenceTableNamespace.DATE, date!!)
        parseObject.put(UserPreferenceTableNamespace.PREFERRED_OPTION, preferredOption!!)
        parseObject.put(UserPreferenceTableNamespace.STATUS, status!!)
        parseObject.put(UserPreferenceTableNamespace.NOTE, note!!)
        parseObject.put(UserPreferenceTableNamespace.ADDRESS, address!!)
        return DatabaseManager.save(parseObject)
    }

    companion object {
        suspend fun  create(
            userId: String,
            date: String,
            preferredOption:String,
            status: String,
            note:String,
            address: String
        ):Boolean{
            val createdParseObject = ParseObject(UserPreferenceTableNamespace.TABLE_NAME)
            createdParseObject.put(UserPreferenceTableNamespace.USER_ID, userId)
            createdParseObject.put(UserPreferenceTableNamespace.DATE, date)
            createdParseObject.put(UserPreferenceTableNamespace.PREFERRED_OPTION, preferredOption)
            createdParseObject.put(UserPreferenceTableNamespace.STATUS, status)
            createdParseObject.put(UserPreferenceTableNamespace.NOTE, note)
            createdParseObject.put(UserPreferenceTableNamespace.ADDRESS, address)
            return DatabaseManager.save(createdParseObject)
        }

        suspend fun read(restaurantId: String, date: String):List<UserPreference>? {
            val response = DatabaseManager.readUserPreference(restaurantId, date)
            return if(response != null && response.isNotEmpty()){
                val result = mutableListOf<UserPreference>()
                response.forEach{
                    result.add(UserPreference(it))
                }
                result
            }else{
                null
            }
        }

        suspend fun read(userId: String):List<UserPreference>? {
            val response = DatabaseManager.readUserPreference(userId)
            return if(response != null && response.isNotEmpty()){
                val result = mutableListOf<UserPreference>()
                response.forEach{
                    result.add(UserPreference(it))
                }
                result
            }else{
                null
            }
        }
    }

}