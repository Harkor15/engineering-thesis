package com.example.lunchforyou.views.restaurant.restaurant_today_orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchforyou.R
import com.example.lunchforyou.database.UserPreference

class RestaurantTodayOrdersAdapter(private val data:List<UserPreference>):
    RecyclerView.Adapter<RestaurantTodayOrdersAdapter.ViewHolder>() {

        class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            val name: TextView = view.findViewById(R.id.rtoi_name)
            val address: TextView = view.findViewById(R.id.rtoi_address)
            val option: TextView = view.findViewById(R.id.rtoi_option)
            val note: TextView = view.findViewById(R.id.rtoi_note)
            val status: TextView = view.findViewById(R.id.rtoi_status)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_today_order_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.name.text = data[pos].userId
        holder.address.text = data[pos].address
        holder.option.text = data[pos].preferredOption
        holder.note.text = data[pos].note
        holder.status.text = data[pos].status
    }

    override fun getItemCount(): Int {
        return data.size
    }

}