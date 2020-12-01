package com.example.notes.screens.menu

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.database.Notes_DB_Dao
import com.example.notes.screens.allRecords.RecordsViewModel
import com.example.notes.screens.randomFact.RandomFactViewModel
import java.lang.IllegalArgumentException

class MenuViewModelFactory (
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("uncheked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)){
            return MenuViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}