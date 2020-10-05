package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setactivity_1(view: View) {
        val intent = Intent(this, EditTextActivity::class.java)
        startActivity(intent)
    }
    fun setactivity_2(view: View) {
        val intent = Intent(this, NotesActivity::class.java)
        startActivity(intent)
    }

    fun setactivity_3(view: View) {
        val intent = Intent(this, OptionsActivity::class.java)
        startActivity(intent)
    }
}