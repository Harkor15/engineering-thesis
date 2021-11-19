package com.example.lunchforyou.utils

import android.content.Context
import com.example.lunchforyou.views.main.MainActivity
import java.lang.Exception
import java.util.*

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

    fun getSubscribedRestaurantToken():String?{
        val token = MainActivity.contextOfApplication.getSharedPreferences(
            SessionNamespace.PREFERENCE_KEY,Context.MODE_PRIVATE)
            .getString(SessionNamespace.SUBSCRIBED_RESTAURANT_TOKEN,"")
        return if (token !="")
            token
        else
            null
    }

    fun setSubscribedRestaurantToken(token:String){
        with (MainActivity.contextOfApplication.getSharedPreferences(
            SessionNamespace.PREFERENCE_KEY,Context.MODE_PRIVATE).edit()){
            putString(SessionNamespace.SUBSCRIBED_RESTAURANT_TOKEN,token)
            apply()
        }
    }

    fun clearAllData(){
        with (MainActivity.contextOfApplication.getSharedPreferences(
            SessionNamespace.PREFERENCE_KEY,Context.MODE_PRIVATE).edit()){
            clear()
            commit()
        }
    }

    fun setSubscriptionExpiration(day:Int, month:Int, year:Int){
        with (MainActivity.contextOfApplication.getSharedPreferences(
            SessionNamespace.PREFERENCE_KEY,Context.MODE_PRIVATE).edit()){
            putString(SessionNamespace.SUBSCRIPTION_EXPIRATION,"$day.$month.$year")
            apply()
        }
    }

    fun getSubscriptionExpiration(): Date? {
        val token = MainActivity.contextOfApplication.getSharedPreferences(
            SessionNamespace.PREFERENCE_KEY,Context.MODE_PRIVATE)
            .getString(SessionNamespace.SUBSCRIPTION_EXPIRATION,"")
        return if (token !=""){
            val spl = token!!.split('.')
            try {
                val cal = Calendar.getInstance()
                cal.set(Calendar.YEAR, spl[2].toInt())
                cal.set(Calendar.MONTH, spl[1].toInt())
                cal.set(Calendar.DAY_OF_MONTH, spl[0].toInt())
                cal.set(Calendar.HOUR, 0)
                cal.set(Calendar.MILLISECOND, 0)
                cal.set(Calendar.MILLISECOND, 0)
                cal.time
            }catch (e:Exception){
                null
            }
        }
        else
            null
    }

}


object SessionNamespace{
    const val PREFERENCE_KEY = "com.example.lunchforyou"
    const val USER_TOKEN = "USER_TOKEN"
    const val IS_RESTAURANT= "IS_RESTAURANT"
    const val SUBSCRIPTION_EXPIRATION= "SUBSCRIPTION_EXPIRATION"
    const val SUBSCRIBED_RESTAURANT_TOKEN = "SUBSCRIBED_RESTAURANT_TOKEN"
}