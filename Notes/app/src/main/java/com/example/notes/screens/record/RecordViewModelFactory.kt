package com.example.notes.screens.record

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.database.Notes_DB_Dao
import java.lang.IllegalArgumentException
import javax.sql.DataSource

class RecordViewModelFactory (
    private val dataSource: Notes_DB_Dao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("uncheked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecordViewModel::class.java)){
            return RecordViewModel(dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
