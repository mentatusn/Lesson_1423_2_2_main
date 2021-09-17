package ru.geekbrains.lesson_1423_2_2_main.view.details

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import okhttp3.*
import ru.geekbrains.lesson_1423_2_2_main.BuildConfig
import ru.geekbrains.lesson_1423_2_2_main.databinding.FragmentDetailsBinding
import ru.geekbrains.lesson_1423_2_2_main.domain.Weather
import ru.geekbrains.lesson_1423_2_2_main.lesson6.MainService
import ru.geekbrains.lesson_1423_2_2_main.repository.WeatherDTO
import ru.geekbrains.lesson_1423_2_2_main.repository.WeatherLoaderListener
import ru.geekbrains.lesson_1423_2_2_main.utils.YANDEX_API_KEY_NAME
import ru.geekbrains.lesson_1423_2_2_main.utils.YANDEX_API_URL
import ru.geekbrains.lesson_1423_2_2_main.utils.YANDEX_API_URL_END_POINT
import java.io.IOException

class DetailsFragment : Fragment(), WeatherLoaderListener {


    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                val weatherDTO = it.getParcelableExtra<WeatherDTO>(DETAILS_LOAD_RESULT_EXTRA)
                if (weatherDTO != null) {
                    showWeather(weatherDTO)
                } else {
                    // HW ERROR
                }
            }

        }
    }

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
        //return inflater.inflate(R.layout.fragment_main, container, false)
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val localWeather: Weather by lazy {
        (arguments?.getParcelable(BUNDLE_WEATHER_KEY)) ?: Weather()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //WeatherLoader(this, localWeather.city.lat, localWeather.city.lon).loadWeather()
        getWeather()
    }

    fun getWeather(){

        val client =OkHttpClient()
        val builder:Request.Builder = Request.Builder()
        builder.header(YANDEX_API_KEY_NAME,BuildConfig.WEATHER_API_KEY)
        builder.url(YANDEX_API_URL+YANDEX_API_URL_END_POINT+"?lat=${localWeather.city.lat}&lon=${localWeather.city.lon}")
        val request:Request = builder.build()
        val call: Call = client.newCall(request)
        call.enqueue(callback) // асинхронно


        /*Thread{
            // дело1
            val response: Response = call.execute()     //синхронно
            val serverResponse: String? = response.body?.string()
            if(response.isSuccessful&&serverResponse!=null){
                val weatherDTO = Gson().fromJson(serverResponse, WeatherDTO::class.java)
                Handler(Looper.getMainLooper()).post {
                    showWeather(weatherDTO)
                }
            }
            //дело 2
        }.start()*/

    }

    val callback =  object : Callback{
        override fun onFailure(call: Call, e: IOException) {
            TODO("Not yet implemented")
        }

        override fun onResponse(call: Call, response: Response) {
            val serverResponse: String? = response.body?.string()
            if(response.isSuccessful&&serverResponse!=null){
                val weatherDTO = Gson().fromJson(serverResponse, WeatherDTO::class.java)
                Handler(Looper.getMainLooper()).post {
                    showWeather(weatherDTO)
                }
            }
        }
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
        LocalBroadcastManager.getInstance(requireActivity()).unregisterReceiver(receiver)
    }

    override fun onLoaded(weatherDTO: WeatherDTO) {
        showWeather(weatherDTO)
    }

    override fun onFailed(throwable: Throwable) {
        // HW
    }

}
