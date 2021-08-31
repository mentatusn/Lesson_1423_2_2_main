package ru.geekbrains.lesson_1423_2_2_main.repository

import ru.geekbrains.lesson_1423_2_2_main.domain.Weather

class RepositoryImpl:Repository {
    override fun getWeatherFromRemoteSource(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalSource(): Weather {
        return Weather()
    }

}