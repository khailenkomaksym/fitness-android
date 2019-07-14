package com.fitness.athome.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fitness.athome.R
import com.fitness.athome.db.exercises.Exercises
import com.fitness.athome.db.exercises.ExercisesDatabase

class DashboardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View =  inflater.inflate(R.layout.fragment_dashboard, container, false)

        Log.d("myLogs", "room-funct start 1")

        var exercisesDatabase: ExercisesDatabase? = activity?.applicationContext?.let { ExercisesDatabase.getInstance(it) }

        val exercisesList: List<Exercises> = exercisesDatabase?.exercisesDao()?.getAllExercises()!!

        Toast.makeText(context, "Size: " + exercisesList.get(0).title, Toast.LENGTH_LONG).show()

        return view;
    }

}