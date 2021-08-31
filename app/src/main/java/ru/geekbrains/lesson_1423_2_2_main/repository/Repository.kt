package ru.geekbrains.lesson_1423_2_2_main.repository

import ru.geekbrains.lesson_1423_2_2_main.domain.Weather

interface Repository {
    fun getWeatherFromRemoteSource():Weather
    fun getWeatherFromLocalSource():Weather
}