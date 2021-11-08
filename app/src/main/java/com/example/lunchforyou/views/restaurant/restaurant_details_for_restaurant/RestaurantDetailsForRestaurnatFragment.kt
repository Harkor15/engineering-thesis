package com.example.lunchforyou.views.restaurant.restaurant_details_for_restaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.lunchforyou.R

class RestaurantDetailsFragment : Fragment() {
    val viewModel = RestaurantDetailsForRestaurantViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resNameEdtx = view.findViewById<EditText>(R.id.rd_restaurant_name_edtx)
        val subPriceEdtx = view.findViewById<EditText>(R.id.rd_subscription_price_et)
        val addressEdtx = view.findViewById<EditText>(R.id.rd_edtx_address)
        val deliveryHoursEdtx = view.findViewById<EditText>(R.id.rd_edtx_delivery_hours)
        val openHoursEdtx = view.findViewById<EditText>(R.id.rd_edtx_open_hours)

        viewModel.response.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        })

        viewModel.restaurantName.observe(viewLifecycleOwner,{
            resNameEdtx.setText(it)
        })

        viewModel.restaurantSubscriptionPrice.observe(viewLifecycleOwner,{
            subPriceEdtx.setText(it)
        })

        viewModel.restaurantAddress.observe(viewLifecycleOwner,{
            addressEdtx.setText(it)
        })

        viewModel.restaurantDeliveryHours.observe(viewLifecycleOwner,{
            deliveryHoursEdtx.setText(it)
        })

        viewModel.restaurantOpenHours.observe(viewLifecycleOwner,{
            openHoursEdtx.setText(it)
        })

        view.findViewById<Button>(R.id.rd_button_back).setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.popBackStack()
        }
        view.findViewById<Button>(R.id.rd_save_btn).setOnClickListener {

            viewModel.saveRestaurantDetails(resNameEdtx.text.toString(),
                subPriceEdtx.text.toString(),addressEdtx.text.toString(),
                deliveryHoursEdtx.text.toString(),openHoursEdtx.text.toString())
        }

        viewModel.init()
    }




}