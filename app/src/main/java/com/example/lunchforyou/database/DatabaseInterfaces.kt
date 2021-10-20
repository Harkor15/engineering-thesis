package com.example.lunchforyou.database

interface IGetClient {
    fun getClientResponse(client:Client)
    fun error(e: String)
}

interface IDeleteClient{
    fun userDeleted()
    fun error(e:String)
}