package ru.geekbrains.lesson_1423_2_2_main.view.main

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.geekbrains.lesson_1423_2_2_main.R
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentMainBinding
import ru.geekbrains.lesson_1423_2_2_main.domain.City
import ru.geekbrains.lesson_1423_2_2_main.domain.Weather
import ru.geekbrains.lesson_1423_2_2_main.view.OnItemViewClickListener
import ru.geekbrains.lesson_1423_2_2_main.view.details.DetailsFragment
import ru.geekbrains.lesson_1423_2_2_main.viewmodel.AppState
import ru.geekbrains.lesson_1423_2_2_main.viewmodel.MainViewModel

class MainFragment : Fragment(), OnItemViewClickListener {


    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() {
            return _binding!!
        }
    private var isDataSetRus: Boolean = true
    private var adapter = MainFragmentAdapter()


    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    private fun checkPermission() {
        context?.let {
            if (ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                getLocation()
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                showRatio()
            } else {
                myRequestPermission()
            }
        }
    }

    private fun showRatio() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_rationale_title)
            .setMessage(R.string.dialog_rationale_message)
            .setPositiveButton(R.string.dialog_rationale_give_access) { dialog, which ->
                myRequestPermission()
            }
            .setNegativeButton(R.string.dialog_rationale_decline) { dialog, which ->
                dialog.dismiss()
            }
            .create().show()
    }

    private val REQUEST_CODE = 999

    private fun myRequestPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation()
                } else {
                    context?.let {
                        showRatio()
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private val onLocationChangeListener = object : LocationListener{
        override fun onLocationChanged(location: Location) {
            getAddressAsync(requireContext(),location)
        }

        override fun onProviderDisabled(provider: String) {
            super.onProviderDisabled(provider)
        }

        override fun onProviderEnabled(provider: String) {
            super.onProviderEnabled(provider)
        }
    }

    private fun getAddressAsync(context: Context, location: Location) {
        val geoCoder = Geocoder(context)
        var address = geoCoder.getFromLocation(location.latitude,location.longitude,1)
        showAddressDialog(address[0].getAddressLine(0),location)
    }

    private fun showAddressDialog(address: String, location: Location) {
        activity?.let {
            androidx.appcompat.app.AlertDialog.Builder(it)
                .setTitle(getString(R.string.dialog_address_title))
                .setMessage(address)
                .setPositiveButton(getString(R.string.dialog_address_get_weather)) { _, _ ->
                    onItemClick(
                        Weather(
                            City(
                                address,
                                location.latitude,
                                location.longitude
                            )
                        )
                    )
                }
                .setNegativeButton(getString(R.string.dialog_button_close)) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }



    private val REFRESH_PERIOD = 10000L
    private val MINIMAL_DISTANCE = 100f


    fun getLocation() {
        activity?.let {
            if (ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val locationManager = it.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    val provider= locationManager.getProvider(LocationManager.GPS_PROVIDER)
                    provider?.let{
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,REFRESH_PERIOD,MINIMAL_DISTANCE,onLocationChangeListener)
                    }

                } else{
                    val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    location?.let{
                        // TODO показать диалог с координатами/адресом
                    }
                }
            } else {
                showRatio()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainFragmentRecyclerView.adapter = adapter
        adapter.setOnItemViewClickListener(this)
        binding.mainFragmentFABLocation.setOnClickListener { checkPermission() }
        binding.mainFragmentFAB.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                isDataSetRus = !isDataSetRus
                if (isDataSetRus) {
                    viewModel.getWeatherFromLocalSourceRus()
                    binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
                } else {
                    viewModel.getWeatherFromLocalSourceWorld()
                    binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
                }

            }
        })
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData()
            .observe(viewLifecycleOwner, Observer<AppState> { appState: AppState ->
                renderData(appState)
            })
        viewModel.getWeatherFromLocalSourceRus()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                val throwable = appState.error
                Snackbar.make(binding.root, "ERROR $throwable", Snackbar.LENGTH_LONG).show()
            }
            AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.SuccessMain -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                val weather = appState.weatherData
                adapter.setWeather(weather)
                Snackbar.make(binding.root, "Success", Snackbar.LENGTH_LONG).show()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(weather: Weather) {
        val bundle = Bundle()
        bundle.putParcelable(DetailsFragment.BUNDLE_WEATHER_KEY, weather)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, DetailsFragment.newInstance(bundle))
            .addToBackStack("")
            .commit()
    }

}
