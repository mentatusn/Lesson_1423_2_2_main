package ru.geekbrains.lesson_1423_2_2_main.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FactDTO  (
    val temp : Int,
    val feels_like : Int,
    val condition : String
):Parcelable


