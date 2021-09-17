package ru.geekbrains.lesson_1423_2_2_main.utils

import ru.geekbrains.lesson_1423_2_2_main.domain.Weather
import ru.geekbrains.lesson_1423_2_2_main.domain.getDefaultCity
import ru.geekbrains.lesson_1423_2_2_main.repository.WeatherDTO

fun convertDtoToModel(weatherDTO: WeatherDTO):Weather{
    return (Weather(getDefaultCity(),weatherDTO.fact.temp,weatherDTO.fact.feels_like,weatherDTO.fact.condition))
}