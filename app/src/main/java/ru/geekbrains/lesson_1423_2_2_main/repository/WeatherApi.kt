package ru.geekbrains.lesson_1423_2_2_main.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.geekbrains.lesson_1423_2_2_main.utils.YANDEX_API_KEY_NAME
import ru.geekbrains.lesson_1423_2_2_main.utils.YANDEX_API_URL_END_POINT
import ru.geekbrains.lesson_1423_2_2_main.utils.YANDEX_API_URL_END_POINT_FACT
import ru.geekbrains.lesson_1423_2_2_main.utils.YANDEX_API_URL_END_POINT_IMG

interface WeatherApi {

    @GET(YANDEX_API_URL_END_POINT)
    fun getWeather(
        @Header(YANDEX_API_KEY_NAME) apikey:String,
        @Query("lat") lat:Double,
        @Query("lon") lon:Double
    ):Call<WeatherDTO>

    @GET(YANDEX_API_URL_END_POINT_FACT)
    fun getFact():Call<FactDTO>

    @GET(YANDEX_API_URL_END_POINT_IMG)
    fun getImage():Call<FactDTO>
}