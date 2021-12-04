package com.example.lunchforyou.views.restaurant.restaurant_messages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import com.example.lunchforyou.R
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchforyou.views.client.client_messages.MessagesAdapter


class RestaurantMessagesFragment : Fragment() {
    val vm = RestaurantMessagesViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.rmess_button_back).setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.popBackStack()
        }
        view.findViewById<ImageView>(R.id.rmess_button_send).setOnClickListener{
            val messBox = view.findViewById<EditText>(R.id.rmess_input_messageText)
            if(messBox.text.toString()!="")
                vm.sendMessage(messBox.text.toString())
            messBox.setText("")
        }
        vm.clients.observe(viewLifecycleOwner,{
            val spinner = view.findViewById<Spinner>(R.id.rmes_spinner)

            val spinnerArrayAdapter: ArrayAdapter<String> =
                ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, it)
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter=spinnerArrayAdapter
            spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    vm.showMessages(position)
                }
            }
        })
        vm.messages.observe(viewLifecycleOwner,{
            val recyclerView = view.findViewById<RecyclerView>(R.id.rmes_list)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter= MessagesAdapter(it)
        })

        vm.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_messages, container, false)
    }


}