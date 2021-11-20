package com.example.lunchforyou.views.client.new_client_main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.lunchforyou.R
import com.example.lunchforyou.utils.TAG


class NewClientMainFragment : Fragment() {
    private val viewModel = NewClientMainViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_new_client_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        val btnSavePersonalData = view.findViewById<Button>(R.id.ncm_btn_save_client_personal_data)
        val btnSaveRestaurantCode = view.findViewById<Button>(R.id.ncm_btn_save_restaurnat_code)
        val edtxClientName = view.findViewById<EditText>(R.id.ncm_edtx_client_name)
        val edtxClientSurname = view.findViewById<EditText>(R.id.ncm_edtx_client_surname)
        val edtxClientAddress = view.findViewById<EditText>(R.id.ncm_edtx_client_address)

        viewModel.forceToFillClientDetails.observe(viewLifecycleOwner,{
            btnSaveRestaurantCode.isEnabled = !it
        })

        viewModel.navigateToMainClient.observe(viewLifecycleOwner,{
            if(it)
                navController.navigate(R.id.action_newClientMainFragment_to_clientMain)
        })

        viewModel.subscribedRestaurant.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(),it, Toast.LENGTH_SHORT).show()
        })

        viewModel.logout.observe(viewLifecycleOwner,{
            Log.d(TAG,"logout")
            navController.navigate(R.id.action_newClientMainFragment_to_authenticationFragment)
        })

        viewModel.saveClientPersonalDataResult.observe(viewLifecycleOwner,{
            btnSavePersonalData.isEnabled=true
            val toastInfo= if(it){
                R.string.personal_data_saved
            }else{
                R.string.error
            }
            Toast.makeText(requireContext(),toastInfo,Toast.LENGTH_SHORT).show()
        })

        /*viewModel.saveRestaurantResult.observe(viewLifecycleOwner,{
            btnSaveRestaurantCode.isEnabled=true

        })*/

        viewModel.navigateToMainRestaurant.observe(viewLifecycleOwner,{
            navController.navigate(R.id.action_newClientMainFragment_to_restaurantMain)
        })

        viewModel.displayClientPersonalData.observe(viewLifecycleOwner,{
            edtxClientName.setText(it.name)
            edtxClientSurname.setText(it.surname)
            edtxClientAddress.setText(it.address)

        })

        view.findViewById<Button>(R.id.ncm_logout).setOnClickListener {
            viewModel.logout()
        }
        view.findViewById<Button>(R.id.ncm_create_new_restaurant).setOnClickListener {
            viewModel.createNewRestaurant()
        }

        btnSaveRestaurantCode.setOnClickListener {
            btnSaveRestaurantCode.isEnabled=false
            viewModel.setSubscribedRestaurantToken(
                view.findViewById<EditText>(R.id.ncm_edtx_restaurant_code).text.toString()
            )
        }

        btnSavePersonalData.setOnClickListener {
            btnSavePersonalData.isEnabled=false
            viewModel.setClientPersonalData(
                edtxClientName.text.toString(),
                edtxClientSurname.text.toString(),
                edtxClientAddress.text.toString(),
            )
        }

        viewModel.init()
    }
}