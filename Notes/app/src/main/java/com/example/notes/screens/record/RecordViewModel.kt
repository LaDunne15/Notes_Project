package com.example.notes.screens.record

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.*
import com.example.notes.database.Notes_DB_Dao
import com.example.notes.database.Record
import com.example.notes.formatRecords
import kotlinx.coroutines.*
import timber.log.Timber

class RecordViewModel(val database: Notes_DB_Dao, application: Application) : AndroidViewModel(application) {

    var text = ""


    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private  val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var record = MutableLiveData<Record?>()

    private var records = database.getAllRecords()

    val recordsString = Transformations.map(records) {
        records -> formatRecords(records, application.resources)
    }

    val visibility = Transformations.map(record) {
        null != it
    }

    private var _showShackBarEvent = MutableLiveData<Boolean>()

    val showShackBarEvent: LiveData<Boolean>
        get() = _showShackBarEvent

    fun doneShowingSnackBar() {
        _showShackBarEvent.value = false
    }

    init {
        //text.value = ""
        initializeRecord()
    }

    private fun initializeRecord(){
        uiScope.launch {
            record.value = getRecordFromDB()
        }
    }

    private suspend fun getRecordFromDB() : Record? {
        return withContext(Dispatchers.IO) {
            var R = database.getLast()
            if(R?.date_create != R?.date_changed) {
                R = null
            }
            R
        }
    }

    fun onStartRecording() {
        uiScope.launch {
            var newRecord = Record(text = text)

            insert(newRecord)

            record.value = getRecordFromDB()

            text=""
        }


    }
    private suspend fun insert(record: Record)
    {
        withContext(Dispatchers.IO){
            database.insert(record)
        }
    }

    fun onClear()
    {
        uiScope.launch {
            clear()
            record.value = null

            _showShackBarEvent.value = true
        }
    }

    suspend fun clear()
    {
        withContext(Dispatchers.IO) {
            database.delete()
        }
    }







}