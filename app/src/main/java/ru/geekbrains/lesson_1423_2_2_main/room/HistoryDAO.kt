package ru.geekbrains.lesson_1423_2_2_main.room

import android.database.Cursor
import androidx.room.*



const val ID = "id"
const val NAME = "name"
const val TEMPERATURE = "temperature"
@Dao
interface HistoryDAO {

    @Query("SELECT * FROM HistoryEntity")
    fun all():List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE name LIKE :name")
    fun getDataByWord(name :String): List<HistoryEntity>


    @Query("DELETE FROM HistoryEntity WHERE id=:id")
    fun deleteById(id: Long)

    @Delete
    fun delete(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Query("SELECT id,name,temperature FROM HistoryEntity")
    fun getHistoryCursor(): Cursor

    @Query("SELECT id,name,temperature FROM HistoryEntity WHERE id=:id")
    fun getHistoryCursor(id:Long): Cursor
}
