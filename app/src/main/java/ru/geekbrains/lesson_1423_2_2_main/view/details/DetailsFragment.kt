package ru.geekbrains.lesson_1423_2_2_main.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentDetailsBinding
import ru.geekbrains.lesson_1423_2_2_main.domain.Weather
import ru.geekbrains.lesson_1423_2_2_main.utils.show
import ru.geekbrains.lesson_1423_2_2_main.viewmodel.AppState
import ru.geekbrains.lesson_1423_2_2_main.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {


    private val viewModel:DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    companion object {
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
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val localWeather: Weather by lazy {
        (arguments?.getParcelable(BUNDLE_WEATHER_KEY)) ?: Weather()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner,{
            renderData(it)
        })
        viewModel.getWeatherFromRemoteSource("https://api.weather.yandex.ru/v2/informers?lat=${
            localWeather.city.lat}&lon=${localWeather.city.lon}")
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.INVISIBLE
                binding.mainView.visibility = View.VISIBLE
                val throwable = appState.error
                view?.show("ERROR $throwable","RELOAD") {
                    viewModel.getWeatherFromRemoteSource(
                        "https://api.weather.yandex.ru/v2/informers?lat=${
                            localWeather.city.lat
                        }&lon=${localWeather.city.lon}"
                    )
                }
            }
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
                binding.mainView.visibility = View.INVISIBLE
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.INVISIBLE
                binding.mainView.visibility = View.VISIBLE
                val weather = appState.weatherData
                showWeather(weather[0])
                Snackbar.make(binding.root, "Success", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun showWeather(weather: Weather) {
        with(binding) {
            cityName.text = localWeather.city.name
            cityCoordinates.text = "lat ${localWeather.city.lat}\n lon ${localWeather.city.lon}"
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = "${weather.feelsLike}"
            weatherCondition.text = "${weather.condition}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
