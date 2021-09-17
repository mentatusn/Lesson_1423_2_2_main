package ru.geekbrains.lesson_1423_2_2_main.repository

import okhttp3.Callback

interface DetailsRepository {
    fun getWeatherDetailsFromServer(lat:Double,log:Double,callback: retrofit2.Callback<WeatherDTO>)
}