package com.example.lunchforyou.views.restaurant.restaurant_menu

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import com.example.lunchforyou.R
import java.util.*

class RestaurantMenuFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    lateinit var datePickerEdtx: EditText
    private val vm = RestaurantMenuViewModel()
    private val calendar = Calendar.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.rmenu_button_back).setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.popBackStack()
        }
        datePickerEdtx = view.findViewById(R.id.rmenu_date_picker)
            view.findViewById<ImageView>(R.id.rmenu_calendar_icon).setOnClickListener {

                DatePickerDialog(requireContext(),this,
                    calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        view.findViewById<Button>(R.id.rmenu_btn_save).setOnClickListener {
            vm.save(
                view.findViewById<EditText>(R.id.rmenu_input_option1).text.toString(),
                view.findViewById<EditText>(R.id.rmenu_input_option2).text.toString()
            )
        }
        vm.date.observe(viewLifecycleOwner,{
            datePickerEdtx.setText(it)
        })
        vm.response.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(), it,Toast.LENGTH_SHORT).show()
        })
        vm.option1.observe(viewLifecycleOwner, {
            view.findViewById<EditText>(R.id.rmenu_input_option1).setText(it)
        })
        vm.option2.observe(viewLifecycleOwner,{
            view.findViewById<EditText>(R.id.rmenu_input_option2).setText(it)
        } )


        vm.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_menu, container, false)
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        vm.setDate(p1,p2,p3)
    }


}