package com.fitness.athome.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.fitness.athome.db.exercises.Exercises
import com.fitness.athome.db.exercises.ExercisesDao
import com.fitness.athome.repository.ExercisesRepository
import io.reactivex.Flowable

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val exercisesRepository: ExercisesRepository
    internal val exercisesList: Flowable<List<Exercises>>

    init {
        exercisesRepository = ExercisesRepository(application)
        exercisesList = exercisesRepository.getExercisesList()
    }

}