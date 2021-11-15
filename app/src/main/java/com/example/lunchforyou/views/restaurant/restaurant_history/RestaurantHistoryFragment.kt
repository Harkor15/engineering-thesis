package com.example.lunchforyou.views.restaurant.restaurant_history

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.lunchforyou.R
import com.example.lunchforyou.views.restaurant.restaurant_today_orders.RestaurantOrdersAdapter
import java.text.SimpleDateFormat
import java.util.*

class RestaurantHistoryFragment : Fragment(), DatePickerDialog.OnDateSetListener {
 private val vm = RestaurantHistoryViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.oh_button_back).setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.popBackStack()
        }
        view.findViewById<ImageView>(R.id.rh_calendar_icon).setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(requireContext(),this,
                calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        vm.date.observe(viewLifecycleOwner,{
            val simpleDate = SimpleDateFormat("dd.MM.yyyy")
            view.findViewById<EditText>(R.id.rh_date_picker).setText(simpleDate.format(it))
        })
        vm.data.observe(viewLifecycleOwner,{
            view.findViewById<RecyclerView>(R.id.rh_orders_list).adapter= RestaurantOrdersAdapter(it)
        })

        //vm.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_history, container, false)
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        vm.setDate(p1,p2,p3)
    }


}