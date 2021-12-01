package com.example.lunchforyou.database

object ClientTableNamespace{
    const val TABLE_NAME = "Client"
    const val ID = "id"
    const val TOKEN = "token"
    const val NAME = "name"
    const val SURNAME = "surname"
    const val ADDRESS = "address"
    const val SUBSCRIBED_RESTAURANT_TOKEN = "subscribed_restaurant_token"
}

object RestaurantTableNamespace{
    const val TABLE_NAME = "Restaurant"
    const val ID = "id"
    const val TOKEN = "token"
    const val NAME ="name"
    const val ADDRESS = "address"
    const val SUBSCRIPTION_PRICE="subscriptionPrice"
    const val DELIVERY_HOURS = "deliveryHours"
    const val OPENED_HOURS="openedHours"
}

object SubscriptionTableNamespace{
    const val TABLE_NAME = "Subscription"
    const val ID="id"
    const val RESTAURANT_ID="restaurantId"
    const val USER_ID="userId"
    const val DAY_OF_BOUGHT="dayOfBought"
    const val LAST_DAY_OF_SUBSCRIPTION="lastDayOfSubscription"
}

object MenuDayTableNamespace{
    const val TABLE_NAME = "MenuDay"
    const val ID="id"
    const val RESTAURANT_ID="restaurantId"
    const val DATE="date"
    const val OPTION_1="option_1"
    const val OPTION_2="option_2"
}

object UserPreferenceTableNamespace{
    const val TABLE_NAME = "UserPreference"
    const val ID ="id"
    const val SUBSCRIBED_RESTAURANT="subscribedRestaurant"
    const val USER_ID="userId"
    const val DATE="date"
    const val NOTE="note"
    const val PREFERRED_OPTION="preferredSoups"
    const val ADDRESS = "address"
    const val STATUS="status"
}

object MessageTableNamespace{
    const val TABLE_NAME = "Message"
    const val ID= "id"
    const val CLIENT_ID="clientId"
    const val RESTAURANT_ID="restaurantId"
    const val IS_FROM_CLIENT="isFromClient"
    const val MESSAGE="message"
    const val DATE ="date"
}