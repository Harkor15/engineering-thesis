        package com.example.lunchforyou.database

import com.parse.ParseQuery

import com.parse.ParseObject




class DatabaseManager {

    companion object {
        fun getClient(token: String, response: IGetClient ) {
            val query = ParseQuery.getQuery<ParseObject>(ClientTableNamespace.TABLE_NAME)
            query.whereEqualTo(ClientTableNamespace.TOKEN, token)
            query.orderByDescending(ClientTableNamespace.TOKEN)
            query.findInBackground { objects, e ->
                if (e == null) {
                    if (objects.isEmpty())
                        response.error("No record found")
                    else
                        response.getClientResponse(Client(objects.first()))
                } else {
                    response.error(e.message!!)
                }
            }
        }

        fun deleteClient(token: String, response: IDeleteClient){
            val query = ParseQuery.getQuery<ParseObject>(ClientTableNamespace.TABLE_NAME)
            query.whereEqualTo(ClientTableNamespace.TOKEN, token)
            query.orderByDescending(ClientTableNamespace.TOKEN)
            query.findInBackground { objects, e ->
                if (e == null) {
                    if (objects.isNotEmpty())
                        objects.first().deleteInBackground { e->
                            if(e!=null){
                                response.error(e.message!!)
                            }else{
                                response.userDeleted()
                            }
                            }
                } else {
                    response.error(e.message!!)
                }
            }
        }

        fun updateClient()

        fun createRestaurant(){
            val entity = ParseObject(RESTAURANT_TABLE)
        }

        fun createNewUser(){
            val entity = ParseObject(ClientTableNamespace.TABLE_NAME)
        }

        fun getRestaurant(){

        }

        fun getRestaurantSubscribers(id: String){

        }

        fun getRestaurantUsersPreferences(id:String, day:String){

        }

        fun setOrderStatus(){

        }

        fun setRestaurantDetails(){

        }

        fun getMenu(restaurantId: String, day: String){

        }

        fun setMenu(restaurantId: String, day:String){

        }



        private const val RESTAURANT_TABLE = "Restaurant"
    }
}
