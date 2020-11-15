package com.example.notes

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.notes.database.Notes_DB_Dao
import com.example.notes.database.Record
import com.example.notes.database.Record_DB
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber
import java.io.IOException
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class SleepDatabaseTest {

    private lateinit var R_Dao: Notes_DB_Dao
    private lateinit var db: Record_DB

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, Record_DB::class.java)
            .allowMainThreadQueries()
            .build()
        R_Dao = db.record_DB_Dao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val record = Record()
        R_Dao.insert(record)
        val tonight = R_Dao.getTonight()
        assertEquals(tonight?.sleepQuality, -1)
    }
}