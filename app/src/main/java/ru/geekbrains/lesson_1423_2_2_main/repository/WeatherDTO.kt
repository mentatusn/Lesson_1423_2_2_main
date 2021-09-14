package ru.geekbrains.lesson_1423_2_2_main.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDTO(val fact: FactDTO):Parcelable
