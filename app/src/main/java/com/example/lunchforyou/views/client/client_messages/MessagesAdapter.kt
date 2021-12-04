package com.example.lunchforyou.views.client.client_messages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchforyou.R
import com.example.lunchforyou.database.Message

class MessagesAdapter(private val data:List<Message>):
RecyclerView.Adapter<MessagesAdapter.ViewHolder>(){

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val leftMess: TextView = view.findViewById<TextView>(R.id.mi_left_message)
        val rightMess: TextView = view.findViewById<TextView>(R.id.mi_right_message)
        val leftMessBorder: ConstraintLayout = view.findViewById<ConstraintLayout>(R.id.mi_left_message_border)
        val rightMessBorder: ConstraintLayout = view.findViewById<ConstraintLayout>(R.id.mi_right_message_border)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        if(data[pos].isFromClient){
            holder.rightMess.text=data[pos].message
            holder.leftMessBorder.visibility=View.GONE
        }else{
            holder.leftMess.text=data[pos].message
            holder.rightMessBorder.visibility=View.GONE
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}