package ru.geekbrains.lesson_1423_2_2_main.viewmodel

import ru.geekbrains.lesson_1423_2_2_main.domain.Weather

sealed class AppState{
    object Loading:AppState()
    //data class Success(val weatherData:Weather):AppState()
    data class SuccessMain(val weatherData: List<Weather>):AppState()
    data class SuccessDetails(val weatherData: Weather):AppState()
    data class Error(val error:String):AppState()
}
