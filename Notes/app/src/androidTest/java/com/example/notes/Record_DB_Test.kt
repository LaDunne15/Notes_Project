package com.example.notes

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.notes.database.DBRecord
import com.example.notes.database.Notes_DB_Dao
import com.example.notes.database.DB_Record
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SleepDatabaseTest {

    private lateinit var R_Dao: Notes_DB_Dao
    private lateinit var dbRecord: DB_Record

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        dbRecord = Room.inMemoryDatabaseBuilder(context, DB_Record::class.java)
            .allowMainThreadQueries()
            .build()
        R_Dao = dbRecord.record_DB_Dao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        dbRecord.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val record = DBRecord()
        R_Dao.insert(record)
        val tonight = R_Dao.getTonight()
        assertEquals(tonight?.text, "")
    }
}