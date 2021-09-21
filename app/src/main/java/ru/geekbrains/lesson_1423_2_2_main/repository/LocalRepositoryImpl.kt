package ru.geekbrains.lesson_1423_2_2_main.repository

import ru.geekbrains.lesson_1423_2_2_main.domain.Weather
import ru.geekbrains.lesson_1423_2_2_main.room.HistoryDAO
import ru.geekbrains.lesson_1423_2_2_main.utils.convertHistoryEntityToWeather
import ru.geekbrains.lesson_1423_2_2_main.utils.convertWeatherToHistoryEntity

class LocalRepositoryImpl(private val localDataSource:HistoryDAO):LocalRepository {
    override fun getAllHistory():List<Weather>{
        return convertHistoryEntityToWeather(localDataSource.all())
    }
    override fun saveEntity(weather: Weather){
        localDataSource.insert(convertWeatherToHistoryEntity(weather))
    }
}