package com.js.topkotlinrepo.core.di

import android.content.Context
import com.js.topkotlinrepo.core.di.modules.apiModule
import com.js.topkotlinrepo.core.di.modules.appModule
import com.js.topkotlinrepo.core.di.modules.dataModule
import com.js.topkotlinrepo.core.di.modules.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object Di {

    private val defaultModules =
        listOf(
            appModule,
            apiModule,
            domainModule,
            dataModule
        )

    fun init(context: Context) {
        startKoin {
            androidContext(context)
            modules(defaultModules)
        }
    }
}
