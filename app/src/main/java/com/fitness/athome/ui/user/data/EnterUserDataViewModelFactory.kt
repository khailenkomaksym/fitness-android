package com.fitness.athome.ui.user.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fitness.athome.repository.FirebaseAuthRepository
import com.fitness.athome.repository.FirestoreRepository

class EnterUserDataViewModelFactory(val firestoreRepository: FirestoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(EnterUserDataViewModel::class.java)) {
            return EnterUserDataViewModel(firestoreRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
