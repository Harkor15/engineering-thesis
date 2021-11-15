package com.example.lunchforyou.views.restaurant.restaurant_today_orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchforyou.R


class RestaurantTodayOrdersFragment : Fragment() {
    private val vm = RestaurantTodayOrdersViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.to_button_back).setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.popBackStack()
        }
        vm.data.observe(viewLifecycleOwner,{
            view.findViewById<RecyclerView>(R.id.to_orders_list).adapter = RestaurantOrdersAdapter(it)
        })
        vm.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_today_orders, container, false)
    }
}