package com.example.lunchforyou.database

interface iGetClient {
    fun getClientResponse(client:Client)
    fun error(e: String)
}