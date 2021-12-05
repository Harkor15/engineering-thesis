package com.example.lunchforyou.views.restaurant.restaurant_main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import com.example.lunchforyou.R
import com.example.lunchforyou.utils.TAG


class RestaurantMainFragment : Fragment() {
    val viewModel = RestaurantMainViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
        val resInfo = view.findViewById<ConstraintLayout>(R.id.rm_restaurantInfo)
        val resMenu = view.findViewById<ConstraintLayout>(R.id.rm_menu)
        val resSubs = view.findViewById<ConstraintLayout>(R.id.rm_subscriptions)
        val resHist = view.findViewById<ConstraintLayout>(R.id.rm_history)
        val resOrders = view.findViewById<ConstraintLayout>(R.id.rm_todayOrders)
        val resMess = view.findViewById<ConstraintLayout>(R.id.rm_messages)

        resInfo.setOnClickListener {
            navController.navigate(R.id.action_restaurantMain_to_restaurantDetailsFragment)
        }
        resMenu.setOnClickListener {
            navController.navigate(R.id.action_restaurantMain_to_restaurantMenu)
        }
        resSubs.setOnClickListener {
            navController.navigate(R.id.action_restaurantMain_to_restaurantSubscriptionsFragment)
        }
        resHist.setOnClickListener {
            navController.navigate(R.id.action_restaurantMain_to_restaurantHistoryFragment)
        }
        resOrders.setOnClickListener {
            navController.navigate(R.id.action_restaurantMain_to_restaurantTodayOrdersFragment)
        }
        resMess.setOnClickListener {
            navController.navigate(R.id.action_restaurantMain_to_restaurantMessagesFragment)
        }
        view.findViewById<Button>(R.id.rm_logout).setOnClickListener {
            viewModel.logout()
        }

        viewModel.backToLoginFragment.observe(viewLifecycleOwner,{
            navController.navigate(R.id.action_restaurantMain_to_authenticationFragment)
        })

        viewModel.onlyConfigurationPossible.observe(viewLifecycleOwner,{
            Log.d(TAG, "VISIBILITY:$it")
            val visibility = if(it) View.GONE else View.VISIBLE
            resMenu.visibility=visibility
            resSubs.visibility=visibility
            resHist.visibility=visibility
            resOrders.visibility=visibility
            resMess.visibility=visibility
        })
        viewModel.init()
    }
}