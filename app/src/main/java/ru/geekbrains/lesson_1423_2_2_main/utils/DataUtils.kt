package ru.geekbrains.lesson_1423_2_2_main.utils

import ru.geekbrains.lesson_1423_2_2_main.domain.City
import ru.geekbrains.lesson_1423_2_2_main.domain.Weather
import ru.geekbrains.lesson_1423_2_2_main.domain.getDefaultCity
import ru.geekbrains.lesson_1423_2_2_main.repository.WeatherDTO
import ru.geekbrains.lesson_1423_2_2_main.room.HistoryEntity

fun convertDtoToModel(weatherDTO: WeatherDTO):Weather{
    return (Weather(getDefaultCity(),weatherDTO.fact.temp,weatherDTO.fact.feels_like,weatherDTO.fact.condition))
}

fun convertHistoryEntityToWeather(entityList:List<HistoryEntity>):List<Weather>{
    return entityList.map {
        Weather(City(it.name,0.0,0.0),it.temperature,0,it.condition)
    }
}

fun convertWeatherToHistoryEntity(weather: Weather):HistoryEntity{
    return HistoryEntity(0,weather.city.name,weather.temperature,weather.condition)
}