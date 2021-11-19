package com.example.lunchforyou.views.client.client_restaurant_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.lunchforyou.R


class ClientRestaurantDetails : Fragment() {
val vm =ClientRestaurantDetailsViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.rdfc_button_back).setOnClickListener {
            activity?.onBackPressed()
        }
        vm.restaurantInfo.observe(viewLifecycleOwner,{
            it.
        })

        vm.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_details_for_client, container, false)
    }


}