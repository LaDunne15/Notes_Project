package com.example.notes;

import android.app.Application;

import timber.log.Timber;

public class PusherApplication extends Application {



    @Override
    public void onCreate() {
        Timber.i("onCreate");
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
