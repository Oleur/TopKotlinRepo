package com.js.topkotlinrepo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.js.topkotlinrepo.core.di.Di

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Di.init(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
