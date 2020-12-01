package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DBRecord::class, NumberProperty::class],version = 1,exportSchema = false)
abstract class DB_Record : RoomDatabase() {
    abstract val record_DB_Dao: Notes_DB_Dao
}

private  lateinit var INSTANSE: DB_Record

fun getDatabase(context: Context): DB_Record {
    synchronized(DB_Record::class.java){
        if(!::INSTANSE.isInitialized) {
            INSTANSE = Room.databaseBuilder(context.applicationContext,
                    DB_Record::class.java,
                    "records").build()

            //трабли з міграцією? .fallbackToDestructiveMigration()


        }
    }
    return INSTANSE
}