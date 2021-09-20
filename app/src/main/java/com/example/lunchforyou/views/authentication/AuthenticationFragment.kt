package com.example.lunchforyou.views.authentication

import android.os.Bundle
import android.util.Log
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
import com.example.lunchforyou.utils.TAG



class AuthenticationFragment : Fragment() {
    private val viewModel =  AuthenticationViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "Auth fragment on create")
        val view: View = inflater.inflate(R.layout.fragment_authentication, container, false)
        viewModel.signInInfo.observe(viewLifecycleOwner,{
            view.findViewById<TextView>(R.id.a_error_info).setText(it)
            view.findViewById<TextView>(R.id.a_error_info).visibility=View.VISIBLE
        })
        viewModel.signInResult.observe(viewLifecycleOwner,{
            if(it){
                //TODO: Handle user logged
                Toast.makeText(requireContext(), "Loged", Toast.LENGTH_SHORT).show()
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.a_loginButton).setOnClickListener {
            val login = view.findViewById<EditText>(R.id.a_inputLogin).text.toString()
            val password = view.findViewById<EditText>(R.id.a_inputPassword).text.toString()
            viewModel.signIn(login, password)
//            if(login.isEmpty()||password.isEmpty()){
//                view.findViewById<TextView>(R.id.a_error_info).visibility=View.VISIBLE
//            }else{
//                if(login.first() == 'r' || login.first()=='R'){
//                    navController.navigate(R.id.action_authenticationFragment_to_restaurantMain)
//                }else{
//                    if(login[1]=='n'||login[1]=='N')
//                        navController.navigate(R.id.action_authenticationFragment_to_newClientMainFragment)
//                    else
//                        navController.navigate(R.id.action_authenticationFragment_to_clientMain)
//                }
//            }
        }
        view.findViewById<TextView>(R.id.a_createNewAccountButton).setOnClickListener{
            navController.navigate(R.id.action_authenticationFragment_to_registerFragment)
        }
    }
}