package com.example.lunchforyou.views.client.client_history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchforyou.R


class ClientHistoryFragment : Fragment() {
    val vm = ClientHistoryViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.ch_button_back).setOnClickListener {
            activity?.onBackPressed()
        }
        vm.userPreferences.observe(viewLifecycleOwner,{
            view.findViewById<RecyclerView>(R.id.ch_history_list).adapter = ClientHistoryAdapter(it)
        })

        vm.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_history, container, false)
    }
}