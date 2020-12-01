package com.example.notes.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.notes.database.getDatabase
import com.example.notes.repository.RecordsRepository
import retrofit2.HttpException

class RefreshDataWorker (appContext: Context,params: WorkerParameters): CoroutineWorker(appContext,params){

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = RecordsRepository(database)

        return try {
            repository.refreshRecords()
            Result.success()
        } catch (exception: HttpException) {
            Result.retry()
        }

    }

}