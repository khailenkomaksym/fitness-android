package com.fitness.athome.db.exercises

import androidx.room.Dao
import androidx.room.Query


@Dao
interface ExercisesDao {

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): List<Exercises>

}