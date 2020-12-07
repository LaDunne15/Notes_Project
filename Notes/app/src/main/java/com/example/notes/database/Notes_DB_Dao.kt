package com.example.notes.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Notes_DB_Dao {
    @Insert
    fun insert(record: DBRecord)

    @Update
    fun update(record: DBRecord)

    @Query("SELECT * from records_table WHERE id= :key")
    fun get(key: Long): DBRecord

    @Query("DELETE from records_table")
    fun delete()

    @Query("SELECT * from records_table ORDER BY date_changed DESC")
    fun getAllRecords(): LiveData<List<DBRecord>>

    @Query("SELECT * from records_table ORDER BY date_changed DESC LIMIT 1")
    fun getLast(): DBRecord?

    @Query("SELECT * FROM records_table ORDER BY id DESC LIMIT 1")
    fun getTonight(): DBRecord?




    //

    @Query("select * from numproperty_table ORDER BY text limit 1")
    fun getRecord(): LiveData<NumberProperty>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(numProperty: NumberProperty)


}








