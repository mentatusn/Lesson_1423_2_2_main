package ru.geekbrains.lesson_1423_2_2_main.view

import ru.geekbrains.lesson_1423_2_2_main.domain.Weather

interface OnItemViewClickListener {
    fun onItemClick(weather: Weather)
}