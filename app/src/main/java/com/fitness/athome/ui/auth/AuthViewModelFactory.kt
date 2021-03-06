package com.fitness.athome.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fitness.athome.repository.FirebaseAuthRepository
import com.fitness.athome.repository.FirestoreRepository

class AuthViewModelFactory(val firebaseAuthRepository: FirebaseAuthRepository, val firestoreRepository: FirestoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(firebaseAuthRepository, firestoreRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}