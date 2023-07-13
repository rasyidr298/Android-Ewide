package com.ewide.test.rasyidr

import android.app.Application
import com.ewide.test.rasyidr.di.dataSourceModule
import com.ewide.test.rasyidr.di.databaseModule
import com.ewide.test.rasyidr.di.networkModule
import com.ewide.test.rasyidr.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MyApp : Application() {

    companion object {
        @get:Synchronized
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    viewModelModule,
                    dataSourceModule
                )
            )
        }
    }
}