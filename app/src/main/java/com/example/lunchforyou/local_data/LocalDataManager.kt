package com.example.lunchforyou.local_data

import android.content.Context
import com.example.lunchforyou.views.main.MainActivity

class LocalDataManager {
    fun getUserToken(): String? {
        val token = MainActivity.contextOfApplication.getSharedPreferences(
            SessionNamespace.PREFERENCE_KEY,Context.MODE_PRIVATE)
            .getString(SessionNamespace.USER_TOKEN,"")
        return if (token !="")
            token
        else
            null
    }

    fun setUserToken(token: String){
        with (MainActivity.contextOfApplication.getSharedPreferences(
            SessionNamespace.PREFERENCE_KEY,Context.MODE_PRIVATE).edit()){
            putString(SessionNamespace.USER_TOKEN,token)
            apply()
        }
    }

    fun isUserRestaurant():Boolean {
        return MainActivity.contextOfApplication.getSharedPreferences(
            SessionNamespace.PREFERENCE_KEY, Context.MODE_PRIVATE)
            .getBoolean(SessionNamespace.IS_RESTAURANT, false)
    }

    fun setIsUserRestaurant(isResult: Boolean){
        with (MainActivity.contextOfApplication.getSharedPreferences(
            SessionNamespace.PREFERENCE_KEY,Context.MODE_PRIVATE).edit()){
            putBoolean(SessionNamespace.IS_RESTAURANT,isResult)
            apply()
        }
    }
}


object SessionNamespace{
    const val PREFERENCE_KEY = "com.example.lunchforyou"
    const val USER_TOKEN = "USER_TOKEN"
    const val IS_RESTAURANT= "IS_RESTAURANT"
}