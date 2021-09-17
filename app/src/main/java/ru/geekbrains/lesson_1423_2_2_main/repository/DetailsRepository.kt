package ru.geekbrains.lesson_1423_2_2_main.repository

interface DetailsRepository {
    fun getWeatherDetailsFromServer(lat:Double,log:Double,callback: retrofit2.Callback<WeatherDTO>)
}