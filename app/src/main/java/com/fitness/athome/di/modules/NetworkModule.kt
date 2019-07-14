package com.fitness.athome.di.modules

import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    internal fun provideNetworkModule(): NetworkModule {
        return NetworkModule()
    }
}