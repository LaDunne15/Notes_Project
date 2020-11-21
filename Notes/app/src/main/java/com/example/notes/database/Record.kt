package com.example.notes.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*


//@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "records_table")
data class Record(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name="theme")
    var theme: String = "No theme",

    @ColumnInfo(name = "text")
    var text: String = "",

    @ColumnInfo(name = "date_create")
    var date_create: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "date_changed")
    var date_changed: Long = date_create
)