package com.dungLv

import android.app.Application
import com.dungLv.utils.MyDebugTree
import timber.log.Timber

class App : Application() {
    companion object {
        lateinit var instance: App

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initLog()
    }

    private fun initLog() {
//        if (BuildConfig.DEBUG) {
        Timber.plant(MyDebugTree())
        }
//    }
}