package com.example.notes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notes.network.NumProperty

@Entity(tableName = "numproperty_table")
data class NumberProperty(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name="text")
    val text: String,
    @ColumnInfo(name="year")
    val year: Int,
    @ColumnInfo(name="number")
    val number: Int,
    @ColumnInfo(name="found")
    val found: Boolean,
    @ColumnInfo(name="type")
    val type: String

)


fun NumberProperty.asDomainModel(): NumProperty {
    return NumProperty (
            text = text,
            year = year,
            number = number,
            found = found,
            type = type
        )
}

fun NumProperty.asDatabaseModel(): NumberProperty {
    return NumberProperty(
        text = text,
        year = year,
        number = number,
        found = found,
        type = type
    )
}