package com.example.notes

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber
import java.util.*
import kotlin.math.round

const val T1 = "T1"
const val T2 = "T2"
var timesRan = 0
var timesRan2 = 0

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MenuFragment()
        fragmentTransaction.add(R.id.linearLayout, fragment)
        fragmentTransaction.commit()

        lifecycle.addObserver(MyObserver())

        if(savedInstanceState!=null)
        {
            Timber.i("onSaveInstanceState Called")
            timesRan = savedInstanceState.getInt(T1)
            timesRan2 = savedInstanceState.getInt(T2)
        }
        else
        {
            timesRan = 0
            timesRan2 = 0
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("onSaveInstanceState Called")
        outState.putInt(T1, timesRan)
        outState.putInt(T2, timesRan2)
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

class MyObserver : LifecycleObserver {

    var timer = Timer()
    var timer2 = Timer()

    var task = object: TimerTask() {
        override fun run() = Timber.i("timer passed ${++timesRan} time(s)")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectListener() {
        Timber.i("onResume")
        timer = Timer()
        task = object: TimerTask() {
            override fun run() = Timber.i("timer passed ${++timesRan} time(s)")
        }
        timer.schedule(task, 0, 1000)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnectListener() {
        Timber.i("onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroyListener() {
        Timber.i("onDestroy")
        timer2.cancel()
        Timber.i((round(timesRan.toFloat()/timesRan2.toFloat()*100)).toString()+"% працював додаток в фокусі")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun createListener() {
        Timber.i("onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopListener() {
        Timber.i("onStop")
        timer.cancel()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startListener() {
        Timber.i("onCreate")
        Timber.i("onStart")
        timer2 = Timer()
        task = object: TimerTask() {
            override fun run(): Unit {(++timesRan2)}
        }
        timer2.schedule(task, 0,1000)
    }


}