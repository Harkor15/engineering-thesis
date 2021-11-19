package com.example.lunchforyou.views.client.client_history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchforyou.R
import com.example.lunchforyou.database.UserPreference
import com.example.lunchforyou.views.restaurant.restaurant_subscriptions.RestaurantSubscriptionsAdapter
import java.text.SimpleDateFormat

class ClientHistoryAdapter(private val data: List<UserPreference>):
RecyclerView.Adapter<ClientHistoryAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val date = view.findViewById<TextView>(R.id.coi_date)
        val option = view.findViewById<TextView>(R.id.coi_selected_option)
        val status = view.findViewById<TextView>(R.id.coi_status)
        val note = view.findViewById<TextView>(R.id.ar_codeInput)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.client_order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val df = SimpleDateFormat("dd.MM.yyy")
        holder.date.text=df.format(data[pos].date)
        holder.option.text=data[pos].preferredOption
        holder.status.text=data[pos].status
        holder.note.text=data[pos].note
    }

    override fun getItemCount(): Int {
        return data.size
    }
}