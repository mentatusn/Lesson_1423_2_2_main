package ru.geekbrains.lesson_1423_2_2_main.repository

import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(val listener: WeatherLoaderListener,val lat:Double,val lon:Double) {
    fun loadWeather(){
        val url = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")

        Thread{
            val urlConnection = url.openConnection() as HttpsURLConnection
            urlConnection.requestMethod ="GET"
            urlConnection.addRequestProperty("X-Yandex-API-Key","ceae3d76-b634-4bfd-8ef5-25a327758ae9")
            urlConnection.readTimeout = 10000
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val weatherDTO = Gson().fromJson(reader,WeatherDTO::class.java)
            val handler = Handler(Looper.getMainLooper())
            handler.post { listener.onLoaded(weatherDTO) }
            urlConnection.disconnect()
        }.start()
    }

}