package com.fitness.athome

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.facebook.FacebookSdk
import com.fitness.athome.di.component.AppComponent
import com.fitness.athome.di.component.DaggerAppComponent
import com.fitness.athome.di.modules.SharedPreferencesModule

class App : MultiDexApplication() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .sharedPreferencesModule(SharedPreferencesModule(applicationContext))
            .build()
    }

;

}