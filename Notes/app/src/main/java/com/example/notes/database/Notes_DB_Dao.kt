package com.example.notes.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Notes_DB_Dao {
    @Insert
    fun insert(record: Record)

    @Update
    fun update(record: Record)

    @Query("SELECT * from records_table WHERE id= :key")
    fun get(key: Long): Record

    @Query("DELETE from records_table")
    fun delete()

    @Query("SELECT * from records_table ORDER BY date_changed DESC")
    fun getAllRecords(): LiveData<List<Record>>

    @Query("SELECT * from records_table ORDER BY date_changed DESC LIMIT 1")
    fun getLast(): Record?

    @Query("SELECT * FROM records_table ORDER BY id DESC LIMIT 1")
    fun getTonight(): Record?
}