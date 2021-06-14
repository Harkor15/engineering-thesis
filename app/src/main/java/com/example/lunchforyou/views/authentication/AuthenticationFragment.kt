package com.example.lunchforyou.views.authentication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.lunchforyou.R
import com.example.lunchforyou.utils.TAG



class AuthenticationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "Auth fragment on create")
        val view: View = inflater.inflate(R.layout.fragment_authentication, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.loginButton).setOnClickListener {
            navController.navigate(R.id.action_authenticationFragment_to_restaurantMain)
        }
        view.findViewById<TextView>(R.id.createNewAccountButton).setOnClickListener{
            navController.navigate(R.id.action_authenticationFragment_to_registerFragment)
        }
    }
}