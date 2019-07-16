package com.fitness.athome.db.exercises

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Flowable


@Dao
interface ExercisesDao {

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flowable<List<Exercises>>

}