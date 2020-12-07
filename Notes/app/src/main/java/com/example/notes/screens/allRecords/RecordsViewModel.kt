package com.example.notes.screens.allRecords

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.notes.database.DBRecord
import com.example.notes.database.DB_Record
import com.example.notes.database.Notes_DB_Dao
import com.example.notes.filteredRecords
import com.example.notes.formatRecords
import kotlinx.coroutines.*
import timber.log.Timber

class RecordsViewModel(val database: Notes_DB_Dao, application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    init {
        _text.value = "asasasas"
    }

    private val _currentchip = MutableLiveData<String>()
    val currentchip: LiveData<String>
        get() = _currentchip

    var records = database.getAllRecords()

    fun withFilter(filter: String){

        records = Transformations.map(records) {
            it -> filteredRecords(it,filter)
        }

    }


}