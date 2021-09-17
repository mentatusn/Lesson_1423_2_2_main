package ru.geekbrains.lesson_1423_2_2_main.repository

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.geekbrains.lesson_1423_2_2_main.BuildConfig
import ru.geekbrains.lesson_1423_2_2_main.utils.YANDEX_API_KEY_NAME

class RemoteDataSource {
    fun getWeatherDetails(requestLink:String,callback: Callback){
        val client = OkHttpClient()
        val builder: Request.Builder = Request.Builder()
        builder.header(YANDEX_API_KEY_NAME, BuildConfig.WEATHER_API_KEY)
        builder.url(requestLink)
        val request: Request = builder.build()
        val call: Call = client.newCall(request)
        call.enqueue(callback) // асинхронно
    }
}