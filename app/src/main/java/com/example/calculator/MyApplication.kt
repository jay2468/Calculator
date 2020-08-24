package com.example.calculator

import android.app.Application
import com.example.calculator.module.myModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@MyApplication)
            // module
            modules(myModule)
        }
    }
}
