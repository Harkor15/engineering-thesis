package com.example.lunchforyou.views.restaurant.restaurant_subscriptions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchforyou.R
import com.example.lunchforyou.database.Subscription

class RestaurantSubscriptionsAdapter(private val data: List<Subscription>):
    RecyclerView.Adapter<RestaurantSubscriptionsAdapter.ViewHolder>(){


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val subscriber: TextView = view.findViewById(R.id.rsi_name)
        val subsSince: TextView = view.findViewById(R.id.rsi_sub_since)
        val endDate: TextView = view.findViewById(R.id.rsi_end_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_subscription_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.subscriber.text=data[pos].userId
        holder.subsSince.text=data[pos].dayOfBought.toString()
        holder.endDate.text=data[pos].lastDayOfSub.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}