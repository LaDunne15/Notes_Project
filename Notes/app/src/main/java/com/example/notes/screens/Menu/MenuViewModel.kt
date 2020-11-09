package com.example.notes.screens.Menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import java.util.*
import kotlin.math.round

class MenuViewModel : ViewModel()
{

    var timesRan = 0
    var timesRan2 = 0

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score
    private val _score2 = MutableLiveData<Int>()
    val score2: LiveData<Int>
        get() = _score2

    var timer = Timer()
    var timer2 = Timer()

    fun increment() {
        timesRan++
    }

    fun increment_v2() {
        _score.value = (_score.value)?.plus(1)
    }

    fun increment2_v2() {
        _score2.value = (_score2.value)?.plus(1)
    }

    fun increment2() {
        timesRan2++
    }
/*
    var task = object: TimerTask() {
        override fun run(): Unit {
            increment()
            Timber.i("timer passed "+timesRan.toString()+"time(s)")
        }
    }*/
/*
    private val _timesRan = MutableLiveData<Int>()
    val timesRan: LiveData<Int>
        get() = _timesRan

    private val _timesRan2 = MutableLiveData<Int>()
    val timesRan2: LiveData<Int>
        get() = _timesRan2
*/





    init {
        //_score.value=0
        Timber.i("MenuViewModel Created!")
    }



    fun createTimer()
    {
        timesRan = 0
        timesRan2 = 0
    }

    fun StartTimer()
    {
        timer2 = Timer()
        var task = object: TimerTask() {
            override fun run(): Unit {
                increment2()
            }
        }
        timer2.schedule(task, 0,1000)
    }

    fun ResumeTimer()
    {
        timer = Timer()
        var task = object: TimerTask() {
            override fun run() : Unit {
                increment()
                Timber.i("timer passed " +timesRan.toString()+ "time(s)")
            }
        }
        timer.schedule(task, 0, 1000)
    }

    fun PauseTimer()
    {
        timer.cancel()
    }

    fun DestroyTimer()
    {
        val t1 = timesRan
        val t2 = timesRan2

        timer2.cancel()
        Timber.i(round((t1.toDouble() / t2.toDouble() * 100)).toString()+"% працював додаток в фокусі. "+ t2.toString()+"s додаток працював всього. "+ t1.toString()+"s додаток працював в фокусі")

    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("MenuViewModel destroyed!")
    }
}