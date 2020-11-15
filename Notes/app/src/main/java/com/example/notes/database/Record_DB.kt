package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Record::class],version = 3,exportSchema = false)
abstract class Record_DB : RoomDatabase(){
    abstract val record_DB_Dao: Notes_DB_Dao

    companion object {
        @Volatile
        private var INSTANCE : Record_DB? = null

        fun getInstance(context: Context) : Record_DB {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null)
                {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Record_DB::class.java,
                        "Record_DB_History"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return  instance
            }
        }
    }

}