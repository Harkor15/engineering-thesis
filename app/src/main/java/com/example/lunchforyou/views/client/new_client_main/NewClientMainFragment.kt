package com.example.lunchforyou.views.client.new_client_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import com.example.lunchforyou.R


class NewClientMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_new_client_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        /*//TODO: IF LOGED
        var haveRestaurantAdded= true
        if(haveRestaurantAdded){
            navController.navigate(R.id.action_newClientMainFragment_to_clientMain)
        }*/

        view.findViewById<ConstraintLayout>(R.id.ncm_addRestaurant).setOnClickListener {
            navController.navigate(R.id.action_newClientMainFragment_to_addRestaurantFragment)
        }
        view.findViewById<ConstraintLayout>(R.id.ncm_settings).setOnClickListener {
            navController.navigate(R.id.action_newClientMainFragment_to_clientSettingsFragment)
        }

    }



}