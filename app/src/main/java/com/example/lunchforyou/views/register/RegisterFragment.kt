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
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.lunchforyou.R




class RegisterFragment : Fragment() {
    private val viewModel = RegisterViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_register, container, false)
        viewModel.registrationInfo.observe(viewLifecycleOwner, {
            requireView().findViewById<TextView>(R.id.regInfo).setText(it)
            requireView().findViewById<TextView>(R.id.regInfo).visibility=View.VISIBLE
        })
        viewModel.registerResult.observe(viewLifecycleOwner,{
            if(it){
                Toast.makeText(requireContext(), "Registered!", Toast.LENGTH_SHORT).show()
                val navController = Navigation.findNavController(view)
                navController.popBackStack()
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.registerButton)?.setOnClickListener {
            viewModel.signUp(
                view.findViewById<EditText>(R.id.regInputMail).text.toString(),
                view.findViewById<EditText>(R.id.regInputPassword).text.toString(),
                view.findViewById<EditText>(R.id.regInputPassword2).text.toString(),
            )
        }
    }
}