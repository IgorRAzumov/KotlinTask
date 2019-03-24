package com.example.kotlinlessons

import android.app.Application
import com.example.kotlinlessons.di.appModule
import com.example.kotlinlessons.di.mainModule
import com.example.kotlinlessons.di.noteModule
import com.example.kotlinlessons.di.splashModule
import com.github.ajalt.timberkt.Timber
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(timber.log.Timber.DebugTree())

        startKoin(this, listOf(appModule, mainModule, noteModule, splashModule))
    }
}