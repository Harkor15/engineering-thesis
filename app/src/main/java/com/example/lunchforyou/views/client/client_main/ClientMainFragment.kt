package com.example.lunchforyou.views.client.client_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.lunchforyou.R

class ClientMainFragment : Fragment() {
    private val viewModel = ClientMainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_client_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        view.findViewById<ConstraintLayout>(R.id.cm_restaurantInfo).setOnClickListener {
            navController.navigate(R.id.action_clientMain_to_clientRestaurantDetails)
        }
        view.findViewById<ConstraintLayout>(R.id.cm_menu).setOnClickListener {
            navController.navigate(R.id.action_clientMain_to_clientMenuFragment)
        }
        view.findViewById<ConstraintLayout>(R.id.cm_subscriptions).setOnClickListener {
            navController.navigate(R.id.action_clientMain_to_clientSubscriptionFragment)
        }
        view.findViewById<ConstraintLayout>(R.id.cm_history).setOnClickListener {
            navController.navigate(R.id.action_clientMain_to_clientHistoryFragment)
        }
        view.findViewById<ConstraintLayout>(R.id.cm_Messages).setOnClickListener {
            navController.navigate(R.id.action_clientMain_to_clientMessagesFragment)
        }
        view.findViewById<ConstraintLayout>(R.id.cm_settings).setOnClickListener {
            navController.navigate(R.id.action_clientMain_to_clientSettingsFragment)
        }
        view.findViewById<Button>(R.id.cm_logout).setOnClickListener {
            viewModel.logout()
        }

        viewModel.backToLoginFragment.observe(viewLifecycleOwner,{
            navController.navigateUp()
            //navController.popBackStack()
        })

        viewModel.init()
    }

}