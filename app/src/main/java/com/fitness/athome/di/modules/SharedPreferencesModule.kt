package com.fitness.athome.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.fitness.athome.R
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule(private val context: Context) {

    @Provides
    internal fun provideContext(): Context {
        return context
    }

    @Provides
    internal fun provideSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(context.getString(R.string.pref_name), Context.MODE_PRIVATE)
    }
}