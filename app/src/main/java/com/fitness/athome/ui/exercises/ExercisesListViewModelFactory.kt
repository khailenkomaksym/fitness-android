package com.fitness.athome.ui.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fitness.athome.repository.FirebaseAuthRepository
import com.fitness.athome.repository.FirestoreRepository

class ExercisesListViewModelFactory(val firestoreRepository: FirestoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ExercisesListViewModel::class.java)) {
            return ExercisesListViewModel(firestoreRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
