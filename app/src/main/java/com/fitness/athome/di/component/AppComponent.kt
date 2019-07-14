package com.fitness.athome.di.component

import com.fitness.athome.di.modules.ContextModule
import com.fitness.athome.di.modules.NetworkModule
import com.fitness.athome.di.modules.RetrofitModule
import com.fitness.athome.di.modules.SharedPreferencesModule
import com.fitness.athome.retrofit.APIInterface
import com.fitness.athome.ui.main.MainActivity
import dagger.Component

@Component(modules = [NetworkModule::class, ContextModule::class, SharedPreferencesModule::class, RetrofitModule::class])
interface AppComponent {

    val apiInterface: APIInterface
    fun injectsMainActivity(mainActivity: MainActivity)
}