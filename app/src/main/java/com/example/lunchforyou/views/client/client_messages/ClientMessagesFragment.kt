package com.example.lunchforyou.views.client.client_messages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchforyou.R
import com.example.lunchforyou.views.client.client_history.ClientHistoryAdapter

class ClientMessagesFragment : Fragment() {
    val vm = ClientMessagesViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.rmess_button_back).setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.popBackStack()
        }
        view.findViewById<ImageView>(R.id.cmess_button_send).setOnClickListener{
            val messBox = view.findViewById<EditText>(R.id.cmess_input_messageText)
            if(messBox.text.toString()!="")
                vm.sendMessage(messBox.text.toString())
            messBox.setText("")
        }

        vm.info.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        vm.messages.observe(viewLifecycleOwner,{
            val recyclerView = view.findViewById<RecyclerView>(R.id.cmess_list)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = MessagesAdapter(it)
        })

        vm.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_messages, container, false)
    }

}