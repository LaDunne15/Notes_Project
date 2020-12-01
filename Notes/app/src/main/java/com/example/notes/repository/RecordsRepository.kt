package com.example.notes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.notes.database.DB_Record
import com.example.notes.database.asDatabaseModel
import com.example.notes.database.asDomainModel
import com.example.notes.network.Network
import com.example.notes.network.NumProperty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecordsRepository (private val database: DB_Record)
{

    val record: LiveData<NumProperty> = Transformations.map(database.record_DB_Dao.getRecord()) {
        it.asDomainModel()
    }


    suspend fun refreshRecords() {
        withContext(Dispatchers.IO) {
            val record = Network.devbytes.get().await()
            database.record_DB_Dao.insert(record.asDatabaseModel())
        }
    }
}