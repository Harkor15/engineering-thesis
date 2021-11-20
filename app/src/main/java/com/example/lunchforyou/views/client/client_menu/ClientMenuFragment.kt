package com.example.lunchforyou.views.client.client_menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import com.example.lunchforyou.R
import java.text.SimpleDateFormat

class ClientMenuFragment : Fragment() {
    private val vm = ClientMenuViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val option1 =view.findViewById<TextView>(R.id.cmenu_option1)
        val option2 =view.findViewById<TextView>(R.id.cmenu_option2)
        val note =view.findViewById<TextView>(R.id.cmenu_edtx_note)
        val date =view.findViewById<TextView>(R.id.cmenu_date)
        val radioButton1 = view.findViewById<RadioButton>(R.id.cmenu_radio_1)
        val radioButton2 = view.findViewById<RadioButton>(R.id.cmenu_radio_2)

        view.findViewById<Button>(R.id.cmenu_button_back).setOnClickListener {
            val navController = Navigation.findNavController(view)
            navController.popBackStack()
        }
        view.findViewById<Button>(R.id.cmenu_btn_save).setOnClickListener {
            val selectedOption =  if(radioButton2.isChecked)
                option2.text.toString()
            else
                option1.text.toString()

            vm.setPreference(selectedOption, note.text.toString()
            )
        }
        vm.menu.observe(viewLifecycleOwner,{
            option1.text = it.option1
            option2.text = it.option2
            val sdf = SimpleDateFormat("dd.MM.yyyy")
            date.text = sdf.format(it.date!!)
        })
        vm.userPreference.observe(viewLifecycleOwner,{
            radioButton1.isChecked=it.preferredOption==option1.text.toString()
            radioButton2.isChecked=it.preferredOption==option2.text.toString()
            note.text = it.note
        })
        vm.info.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        vm.init()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_menu, container, false)
    }


}