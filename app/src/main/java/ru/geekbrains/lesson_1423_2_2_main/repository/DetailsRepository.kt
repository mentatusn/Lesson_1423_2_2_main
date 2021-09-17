package ru.geekbrains.lesson_1423_2_2_main.repository

import okhttp3.Callback

interface DetailsRepository {
    fun getWeatherDetailsFromServer(requestLink:String,callback: Callback)
}