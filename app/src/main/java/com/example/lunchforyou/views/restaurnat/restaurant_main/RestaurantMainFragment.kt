package com.example.lunchforyou.views.restaurnat.restaurant_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import com.example.lunchforyou.R


class RestaurantMainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = Navigation.findNavController(view)
        view.findViewById<ConstraintLayout>(R.id.rm_restaurantInfo).setOnClickListener {
            navController.navigate(R.id.action_restaurantMain_to_restaurantDetailsFragment)
        }
        view.findViewById<ConstraintLayout>(R.id.rm_menu).setOnClickListener {
            navController.navigate(R.id.action_restaurantMain_to_restaurantMenu)
        }
        view.findViewById<ConstraintLayout>(R.id.rm_subscriptions).setOnClickListener {
            navController.navigate(R.id.action_restaurantMain_to_restaurantSubscriptionsFragment)
        }
        view.findViewById<ConstraintLayout>(R.id.rm_history).setOnClickListener {
            navController.navigate(R.id.action_restaurantMain_to_restaurantHistoryFragment)
        }
        view.findViewById<ConstraintLayout>(R.id.rm_todayOrders).setOnClickListener {
            navController.navigate(R.id.action_restaurantMain_to_restaurantTodayOrdersFragment)
        }
        view.findViewById<ConstraintLayout>(R.id.rm_messages).setOnClickListener {
            navController.navigate(R.id.action_restaurantMain_to_restaurnatMessagesFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}