package ru.geekbrains.lesson_1423_2_2_main.repository

interface WeatherLoaderListener {
    fun onLoaded(weatherDTO: WeatherDTO)
    fun onFailed(throwable: Throwable)
}