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

    /*private fun reInit(parseObject: ParseObject) {
        this.parseObject = parseObject
        id = parseObject.getString(ClientTableNamespace.ID)
        token = parseObject.getString(ClientTableNamespace.TOKEN)
        name = parseObject.getString(ClientTableNamespace.NAME)
        surname = parseObject.getString(ClientTableNamespace.SURNAME)
        address = parseObject.getString(ClientTableNamespace.ADDRESS)
        subscribedRestaurantToken =
            parseObject.getString(ClientTableNamespace.SUBSCRIBED_RESTAURANT_TOKEN)
    }*/

    suspend fun update(): Boolean {
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

    suspend fun delete(): Boolean {
        return DatabaseManager.deleteClient(parseObject)
    }



    companion object {
        suspend fun create(
            token: String,
            name: String,
            surname: String,
            address: String,
            subscribedRestaurantToken: String?
        ):Boolean {
            val createdParseObject = ParseObject(ClientTableNamespace.TABLE_NAME)
            createdParseObject.put(ClientTableNamespace.TOKEN, token)
            createdParseObject.put(ClientTableNamespace.NAME, name)
            createdParseObject.put(ClientTableNamespace.SURNAME, surname)
            createdParseObject.put(ClientTableNamespace.ADDRESS, address)
            if (subscribedRestaurantToken != null)
                createdParseObject.put(
                    ClientTableNamespace.SUBSCRIBED_RESTAURANT_TOKEN,
                    subscribedRestaurantToken
                )
            return DatabaseManager.save(createdParseObject)
        }

        suspend fun read(token: String): Client? {
            val response = DatabaseManager.readClient(token)
            return if (response != null) {
                Client(response)
            } else {
                null
            }
        }
    }
}