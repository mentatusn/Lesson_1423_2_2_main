package ru.geekbrains.lesson_1423_2_2_main.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(HistoryEntity::class),version = 1,exportSchema = false)
abstract class HistoryDataBase: RoomDatabase() {
    abstract fun historyDAO():HistoryDAO
}

