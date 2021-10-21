package com.example.lunchforyou.database

import com.parse.ParseObject

interface IGetClient {
    fun getClientResponse(client:Client)
    fun error(e: String)
}

interface IDeleteClient{
    fun userDeleted()
    fun error(e:String)
}

interface DatabaseResponseInterface{
    fun success(table:String?, operation: DatabaseOperationType?)
    fun error(e:String,     table:String?, operation: DatabaseOperationType?)
    fun readed(parseObject: ParseObject)
}

enum class DatabaseOperationType{
    CREATE, READ, UPDATE, DELETE
}