package ru.geekbrains.lesson_1423_2_2_main.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(val name: String, val lat: Double, val lon: Double) : Parcelable
