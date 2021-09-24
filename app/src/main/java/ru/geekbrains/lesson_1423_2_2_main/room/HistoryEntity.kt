package ru.geekbrains.lesson_1423_2_2_main.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0,
    val name: String="",
    val temperature: Int = 0,
    val condition: String =""
)
