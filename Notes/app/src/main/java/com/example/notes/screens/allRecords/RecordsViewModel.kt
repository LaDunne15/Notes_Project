package com.example.notes.screens.allRecords

import android.app.Application
import androidx.lifecycle.*
import com.example.notes.database.Notes_DB_Dao
import com.example.notes.database.Record
import com.example.notes.formatRecords
import kotlinx.coroutines.*

class RecordsViewModel(val database: Notes_DB_Dao, application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    init {
        _text.value = "asasasas"
    }

    var records = database.getAllRecords()

    val recordsString = Transformations.map(records) {
            records -> formatRecords(records, application.resources)
    }

}