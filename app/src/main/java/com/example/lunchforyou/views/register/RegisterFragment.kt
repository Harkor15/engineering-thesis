package com.example.lunchforyou.views.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.lunchforyou.R




class RegisterFragment : Fragment() {
    private val viewModel = RegisterViewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.reg_btn_back).setOnClickListener {
            navController.popBackStack()
        }
        viewModel.registrationInfo.observe(viewLifecycleOwner, {
            requireView().findViewById<TextView>(R.id.regInfo).setText(it)
            requireView().findViewById<TextView>(R.id.regInfo).visibility=View.VISIBLE
        })
        viewModel.registerResult.observe(viewLifecycleOwner,{
            if(it){
                Toast.makeText(requireContext(), "Registered!", Toast.LENGTH_SHORT).show()

                navController.popBackStack()
            }
        })
        view.findViewById<Button>(R.id.registerButton)?.setOnClickListener {
            viewModel.signUp(
                view.findViewById<EditText>(R.id.regInputMail).text.toString(),
                view.findViewById<EditText>(R.id.regInputPassword).text.toString(),
                view.findViewById<EditText>(R.id.regInputPassword2).text.toString(),
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }
}