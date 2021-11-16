package com.example.lunchforyou.views.client.client_subscription

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lunchforyou.R

class ClientSubscriptionFragment : Fragment() {
    val vm = ClientSubscriptionViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.csu_button_back).setOnClickListener {
            activity?.onBackPressed()
        }
        vm.restaurantName.observe(viewLifecycleOwner,{
            view.findViewById<TextView>(R.id.csu_restaurant_name).text = it
        })
        vm.subscriptionActive.observe(viewLifecycleOwner,{
            view.findViewById<TextView>(R.id.csu_sub_active).setText(it)
        })
        vm.subscriptionUntil.observe(viewLifecycleOwner,{
            view.findViewById<TextView>(R.id.csu_sub_last_day)
        })
        vm.subscriptionSince.observe(viewLifecycleOwner,{
            view.findViewById<TextView>(R.id.csu_sub_since)
        })
        vm.toast.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        view.findViewById<Button>(R.id.csu_btn_extend).setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage(R.string.do_you_want_buy_additional_month_of_sub)
            builder.setNegativeButton(R.string.no,null)
            builder.setPositiveButton(R.string.yes,  DialogInterface.OnClickListener { _, _ ->
                vm.extendSub()
            })
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_subscription, container, false)
    }


}