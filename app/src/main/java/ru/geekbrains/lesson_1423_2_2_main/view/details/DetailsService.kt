package ru.geekbrains.lesson_1423_2_2_main.view.details

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import ru.geekbrains.lesson_1423_2_2_main.BuildConfig
import ru.geekbrains.lesson_1423_2_2_main.lesson6.TEST_BROADCAST_INTENT_FILTER
import ru.geekbrains.lesson_1423_2_2_main.lesson6.THREADS_FRAGMENT_BROADCAST_EXTRA
import ru.geekbrains.lesson_1423_2_2_main.repository.WeatherDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

const val LATITUDE_EXTRA = "Latitude"
const val LONGITUDE_EXTRA = "Longitude"


const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
class DetailsService(name:String = "details"): IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        intent?.let{
            val lat = it.getDoubleExtra(LATITUDE_EXTRA,-1.0)
            val lon = it.getDoubleExtra(LONGITUDE_EXTRA,-1.0)
            loadWeather(lat,lon)
        }
    }

    fun loadWeather(lat:Double,lon:Double){
        val url = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
        Thread{
            val urlConnection = url.openConnection() as HttpsURLConnection
            urlConnection.requestMethod ="GET"
            urlConnection.addRequestProperty("X-Yandex-API-Key",BuildConfig.WEATHER_API_KEY)//TODO нужно разобрать вынос в файл
            urlConnection.readTimeout = 10000
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val weatherDTO = Gson().fromJson(reader, WeatherDTO::class.java)
            val handler = Handler(Looper.getMainLooper())


            val mySendIntent = Intent(DETAILS_INTENT_FILTER )
            mySendIntent.putExtra(DETAILS_LOAD_RESULT_EXTRA,weatherDTO)
            LocalBroadcastManager.getInstance(this).sendBroadcast(mySendIntent)

            urlConnection.disconnect()
        }.start()
    }
}