package com.fitness.athome.ui.exercises

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.fitness.athome.R
import com.fitness.athome.db.exercises.Exercises
import com.fitness.athome.repository.ExercisesRepository
import com.fitness.athome.repository.FirestoreRepository
import io.reactivex.Flowable

class ExercisesListViewModel(application: Application) : AndroidViewModel(application) {

    private val exercisesRepository: ExercisesRepository
    internal val exercisesList: Flowable<List<Exercises>>

    init {
        exercisesRepository = ExercisesRepository(application)
        exercisesList = exercisesRepository.getExercisesList()
    }
}