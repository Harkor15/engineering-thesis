package com.example.lunchforyou.views.restaurant.restaurant_subscriptions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchforyou.R

class RestaurantSubscriptionsFragment : Fragment() {
val vm = RestaurantSubscriptionsViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.rsub_button_back).setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.popBackStack()
        }
        vm.activeSubscribers.observe(viewLifecycleOwner,{
            view.findViewById<TextView>(R.id.rsub_active_subs).setText(it)
        })
        vm.allSubscribers.observe(viewLifecycleOwner,{
            view.findViewById<TextView>(R.id.rsub_all_subs).setText(it)
        })
        vm.lostSubscribers.observe(viewLifecycleOwner,{
            view.findViewById<TextView>(R.id.rsub_lost_subs).setText(it)
        })
        vm.newSubscribers.observe(viewLifecycleOwner,{
            view.findViewById<TextView>(R.id.rsub_new_subs).setText(it)
        })
        vm.subscribers.observe(viewLifecycleOwner,{
            view.findViewById<RecyclerView>(R.id.rsub_sub_list).adapter=RestaurantSubscriptionsAdapter(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_subscriptions, container, false)
    }


}