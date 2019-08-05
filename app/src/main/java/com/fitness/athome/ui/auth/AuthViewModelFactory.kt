package com.fitness.athome.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fitness.athome.repository.FirebaseAuthRepository

class AuthViewModelFactory(val firebaseAuthRepository: FirebaseAuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(firebaseAuthRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

/*
class AuthViewModelFactory(firebaseAuthRepository: FirebaseAuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            AuthViewModel(firebaseAuthRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}*/
