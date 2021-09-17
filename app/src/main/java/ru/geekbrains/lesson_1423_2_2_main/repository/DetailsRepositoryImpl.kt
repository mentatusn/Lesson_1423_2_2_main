package ru.geekbrains.lesson_1423_2_2_main.repository

import okhttp3.Callback

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource):DetailsRepository {
    override fun getWeatherDetailsFromServer(lat:Double,log:Double, callback: retrofit2.Callback<WeatherDTO>) {
        remoteDataSource.getWeatherDetails(lat,log,callback)
    }
}