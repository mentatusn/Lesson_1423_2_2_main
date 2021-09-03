package ru.geekbrains.lesson_1423_2_2_main.repository

import ru.geekbrains.lesson_1423_2_2_main.domain.Weather
import ru.geekbrains.lesson_1423_2_2_main.domain.getRussianCities
import ru.geekbrains.lesson_1423_2_2_main.domain.getWorldCities

class RepositoryImpl:Repository {
    override fun getWeatherFromRemoteSource(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalSource(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorageRus(): List<Weather> {
        return getRussianCities()
    }

    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
        return getWorldCities()
    }

}