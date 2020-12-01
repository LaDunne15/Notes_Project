package com.example.notes.screens.randomFact

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.database.Notes_DB_Dao
import com.example.notes.screens.record.RecordViewModel
import java.lang.IllegalArgumentException

class RandomFactViewModelFactory (
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("uncheked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RandomFactViewModel::class.java)){
            return RandomFactViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}