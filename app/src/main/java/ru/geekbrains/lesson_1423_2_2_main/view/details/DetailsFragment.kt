package ru.geekbrains.lesson_1423_2_2_main.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentDetailsBinding
import ru.geekbrains.lesson_1423_2_2_main.domain.Weather
import ru.geekbrains.lesson_1423_2_2_main.repository.WeatherDTO
import ru.geekbrains.lesson_1423_2_2_main.repository.WeatherLoader
import ru.geekbrains.lesson_1423_2_2_main.repository.WeatherLoaderListener

class DetailsFragment : Fragment(), WeatherLoaderListener {


    val listener = object : WeatherLoaderListener {
        override fun onLoaded(weatherDTO: WeatherDTO) {
        }

        override fun onFailed(throwable: Throwable) {
        }
    }

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
        fun newInstance(bundle: Bundle): DetailsFragment {
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

    val localWeather: Weather by lazy {
        (arguments?.getParcelable(BUNDLE_WEATHER_KEY)) ?: Weather()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        WeatherLoader(this, localWeather.city.lat, localWeather.city.lon).loadWeather()
    }

    private fun showWeather(weatherDTO: WeatherDTO) {

        with(binding) {
            cityName.text = localWeather.city.name
            cityCoordinates.text = "lat ${localWeather.city.lat}\n lon ${localWeather.city.lon}"
            temperatureValue.text = weatherDTO.fact.temp.toString()
            feelsLikeValue.text = "${weatherDTO.fact.feels_like}"
            weatherCondition.text = "${weatherDTO.fact.condition}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLoaded(weatherDTO: WeatherDTO) {
        showWeather(weatherDTO)
    }

    override fun onFailed(throwable: Throwable) {
        // HW
    }

}
