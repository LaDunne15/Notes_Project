package com.example.notes

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber
import java.util.*
import kotlin.math.round

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
        Timber.i((round(timesRan.toFloat()/timesRan2.toFloat()*100)).toString()+"% працював додаток в фокусі. "+ timesRan2.toString()+"s додаток працював всього. "+timesRan.toString()+"s додаток працював в фокусі")
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
        Timber.i("onStart")
        timer2 = Timer()
        task = object: TimerTask() {
            override fun run(): Unit {(++timesRan2)}
        }
        timer2.schedule(task, 0,1000)
    }


}