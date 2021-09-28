package ru.geekbrains.lesson_1423_2_2_main.lesson10

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.geekbrains.lesson_1423_2_2_main.R
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentGoogleMapsMainBinding
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentMainBinding
import ru.geekbrains.lesson_1423_2_2_main.lesson9.ContentProviderFragment

class MapsFragment : Fragment() {


    companion object {
        fun newInstance() = MapsFragment()
    }

    private var _binding: FragmentGoogleMapsMainBinding? = null
    private val binding: FragmentGoogleMapsMainBinding
        get() {
            return _binding!!
        }

     lateinit var map:GoogleMap
    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap

        val startPlace = LatLng(55.0, 37.0)
        map.addMarker(MarkerOptions().position(startPlace).title("Marker Start"))
        map.moveCamera(CameraUpdateFactory.newLatLng(startPlace))
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isZoomGesturesEnabled = true

        val isPermissionGranted =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED

        map.setMyLocationEnabled(isPermissionGranted)
        map.uiSettings.isMyLocationButtonEnabled = true


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGoogleMapsMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        binding.buttonSearch.setOnClickListener {
            val geocoder = Geocoder(requireContext())
            val addressRow = binding.searchAddress.text.toString()
            val address = geocoder.getFromLocationName(addressRow,1)
            val location =LatLng(address[0].latitude,address[0].longitude)
            map.clear()
            map.addMarker(MarkerOptions().position(location).title("Marker Start"))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15f))
        }
    }
}