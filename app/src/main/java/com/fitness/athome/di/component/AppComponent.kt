package com.fitness.athome.di.component

import com.fitness.athome.di.modules.*
import com.fitness.athome.retrofit.APIInterface
import com.fitness.athome.ui.auth.AuthActivity
import com.fitness.athome.ui.main.MainActivity
import com.fitness.athome.ui.splash.SplashActivity
import com.fitness.athome.ui.user.data.EnterUserDataActivity
import dagger.Component

@Component(modules = [NetworkModule::class, ContextModule::class, SharedPreferencesModule::class, RetrofitModule::class,
                     FirebaseModule::class])
interface AppComponent {

    fun injectsMainActivity(mainActivity: MainActivity)
    fun injectsSplashActivity(splashActivity: SplashActivity)
    fun injectsAuthActivity(authActivity: AuthActivity)
    fun injectsEnterUserDataActivity(enterUserDataActivity: EnterUserDataActivity)

}