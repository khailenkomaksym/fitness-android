package com.fitness.athome.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.fitness.athome.db.exercises.Exercises
import com.fitness.athome.db.exercises.ExercisesDao
import com.fitness.athome.db.exercises.ExercisesDatabase
import io.reactivex.Flowable

class ExercisesRepository(application: Application) {
    private val exercisesDao: ExercisesDao
    private val exercisesListLiveData: Flowable<List<Exercises>>

    init {
        val exercisesDatabase: ExercisesDatabase = ExercisesDatabase.getInstance(application)
        exercisesDao = exercisesDatabase.exercisesDao()
        exercisesListLiveData = exercisesDao.getAllExercises()
    }

    fun getExercisesList(): Flowable<List<Exercises>> {
        return exercisesListLiveData
    }
}