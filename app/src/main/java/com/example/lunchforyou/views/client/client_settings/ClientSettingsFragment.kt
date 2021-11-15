package com.example.lunchforyou.views.client.client_settings

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.lunchforyou.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.*


class ClientSettingsFragment : Fragment() {
    private val REQUEST_CODE =485
    private lateinit var textInput: EditText
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    val vm = ClientSettingsViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.cs_button_back).setOnClickListener {
            activity?.onBackPressed()
        }
        textInput = view.findViewById(R.id.cs_input_address)
        view.findViewById<Button>(R.id.cs_button_gps).setOnClickListener{
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED -> {
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location : Location? ->
                            giveResult(location?.latitude!!, location.longitude)
                        }
                }
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                    requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE)
            }
                else -> {
                    requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE)
                }
            }
        }

        view.findViewById<Button>(R.id.cs_button_save).setOnClickListener {
            vm.setDetails(
                view.findViewById<EditText>(R.id.cs_input_address).text.toString(),
                view.findViewById<EditText>(R.id.cs_input_name).text.toString(),
                view.findViewById<EditText>(R.id.cs_input_surname).text.toString(),
            )
        }

        vm.response.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        })
        vm.client.observe(viewLifecycleOwner,{
            view.findViewById<EditText>(R.id.cs_input_address).setText(it.address)
            view.findViewById<EditText>(R.id.cs_input_name).setText(it.name)
            view.findViewById<EditText>(R.id.cs_input_surname).setText(it.surname)
        })

        vm.init()
    }

    private fun  giveResult(lat: Double, lon:Double ) {
        val myLocation = Geocoder(requireContext(), Locale.getDefault());
        try {
            val myList = myLocation.getFromLocation(lat, lon, 1);
            if (myList != null && myList.size > 0) {
                val address = myList[0];

                val result = "${address.locality} ${address.thoroughfare} ${address.featureName}"
                textInput.setText(result)
            }
        } catch ( e: IOException) {
            Toast.makeText(requireContext(),R.string.error,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        return inflater.inflate(R.layout.fragment_client_settings, container, false)
    }

}