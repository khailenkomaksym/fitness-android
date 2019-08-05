package com.fitness.athome.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fitness.athome.repository.FirebaseAuthRepository
import com.fitness.athome.repository.FirestoreRepository

class SplashViewModelFactory(val firestoreRepository: FirestoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(firestoreRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
