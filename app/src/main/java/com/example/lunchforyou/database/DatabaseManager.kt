        package com.example.lunchforyou.database

import com.parse.ParseQuery

import com.parse.ParseObject




class DatabaseManager {

    companion object {
                /*fun deleteClient(token: String, response: IDeleteClient){
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
        }*/

//        fun deleteClient(parseObject: ParseObject, response: IDeleteClient?){
//            parseObject.deleteInBackground { e->
//                if(response!=null){
//                    if(e==null)
//                        response.userDeleted()
//                    else
//                        response.error(e.message!!)
//                }
//            }
//        }

        fun createClient(parseObject: ParseObject, response: DatabaseResponseInterface?){
            parseObject.saveInBackground{ e ->
                if(response!=null){
                    if(e==null)
                        response.success(ClientTableNamespace.TABLE_NAME,DatabaseOperationType.CREATE)
                    else
                        response.error(e.message!!, ClientTableNamespace.TABLE_NAME, DatabaseOperationType.CREATE)
                }
            }
        }

        fun readClient(token: String, response: DatabaseResponseInterface ) {
            val query = ParseQuery.getQuery<ParseObject>(ClientTableNamespace.TABLE_NAME)
            query.whereEqualTo(ClientTableNamespace.TOKEN, token)
            query.orderByDescending(ClientTableNamespace.TOKEN)
            query.findInBackground { objects, e ->
                if (e == null) {
                    if (objects.isEmpty())
                        response.error("No record found",ClientTableNamespace.TABLE_NAME,DatabaseOperationType.READ)
                    else
                        response.readed(objects.first())
                } else {
                    response.error(e.message!!,ClientTableNamespace.TABLE_NAME,DatabaseOperationType.READ)
                }
            }
        }

        fun updateClient(parseObject: ParseObject, response: DatabaseResponseInterface?){
            parseObject.saveInBackground{ e ->
                if(response!=null){
                    if(e==null)
                        response.success(ClientTableNamespace.TABLE_NAME,DatabaseOperationType.UPDATE)
                    else
                        response.error(e.message!!, ClientTableNamespace.TABLE_NAME, DatabaseOperationType.UPDATE)
                }
            }
        }

        fun deleteClient(parseObject: ParseObject, response: DatabaseResponseInterface?){
            parseObject.deleteInBackground{e->
                if(response!=null) {
                    if (e != null) {
                        response.error(
                            e.message!!,
                            ClientTableNamespace.TABLE_NAME,
                            DatabaseOperationType.DELETE
                        )
                    } else {
                        response.success(
                            ClientTableNamespace.TABLE_NAME,
                            DatabaseOperationType.DELETE
                        )
                    }
                }
            }
        }

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
