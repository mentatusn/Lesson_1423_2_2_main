package ru.geekbrains.lesson_1423_2_2_main.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentDetailsBinding
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentMainBinding
import ru.geekbrains.lesson_1423_2_2_main.domain.Weather
import ru.geekbrains.lesson_1423_2_2_main.viewmodel.AppState
import ru.geekbrains.lesson_1423_2_2_main.viewmodel.MainViewModel

class DetailsFragment : Fragment() {


    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    companion object {
        /*
        fun newInstance():Fragment{
            return MainFragment()
        }
        */
        fun newInstance(bundle: Bundle):DetailsFragment{
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
        const val BUNDLE_WEATHER_KEY = "key"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_main, container, false)
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather=(arguments?.getParcelable<Weather>(BUNDLE_WEATHER_KEY))?:Weather()
        setData(weather)
    }

    private fun setData(weather: Weather) {
        binding.cityName.text = weather.city.name
        binding.cityCoordinates.text = "lat ${weather.city.lat}\n lon ${weather.city.lon}"
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = "${weather.feelsLike}"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
