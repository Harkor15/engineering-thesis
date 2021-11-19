package com.example.lunchforyou.views.client.client_restaurant_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.lunchforyou.R


class ClientRestaurantDetails : Fragment() {
val vm =ClientRestaurantDetailsViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.rdfc_button_back).setOnClickListener {
            activity?.onBackPressed()
        }
        vm.restaurantInfo.observe(viewLifecycleOwner,{
            view.findViewById<TextView>(R.id.crd_restaurant_name).text = it.name
            view.findViewById<TextView>(R.id.crd_address).text = it.address
            view.findViewById<TextView>(R.id.crd_delivery_hours).text = it.deliveryHours
            view.findViewById<TextView>(R.id.crd_oppening_hours).text = it.openedHours
            view.findViewById<TextView>(R.id.crd_subscription_price).text = it.subscriptionPrice
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