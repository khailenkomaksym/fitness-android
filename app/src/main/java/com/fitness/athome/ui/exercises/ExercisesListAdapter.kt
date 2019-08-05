package com.fitness.athome.ui.exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.athome.R
import com.fitness.athome.db.exercises.Exercises
import kotlinx.android.synthetic.main.item_exercise.view.*

class ExercisesListAdapter(private val listExercises: List<Exercises>) : RecyclerView.Adapter<ExercisesListAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTitle = view.textTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textTitle.text = listExercises.get(position).title
    }

    override fun getItemCount() = listExercises.size
}
