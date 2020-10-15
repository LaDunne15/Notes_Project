package com.example.notes

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MenuFragment()
        fragmentTransaction.add(R.id.linearLayout, fragment)
        fragmentTransaction.commit()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)



        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_main -> {
                val newFragment = MenuFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.linearLayout, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
                true
            }
            R.id.action_settings -> {
                val newFragment = OptionsFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.linearLayout, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
                true
            }
            R.id.action_terms_of_use -> {
                val newFragment = TermsOfUseFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.linearLayout, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
                true
            }
            R.id.action_about -> {
                val newFragment = AboutFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.linearLayout, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
                true
            }
            R.id.action_edit_text -> {
                val newFragment = EditTextFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.linearLayout, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}