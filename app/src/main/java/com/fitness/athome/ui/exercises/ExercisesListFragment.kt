package com.fitness.athome.ui.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitness.athome.R
import com.fitness.athome.db.exercises.Exercises
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ExercisesListFragment : Fragment() {

    lateinit var exercisesListViewModel: ExercisesListViewModel

    private var disposable: Disposable? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var exercisesListAdapter: ExercisesListAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private var exercisesList: ArrayList<Exercises> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_exercises_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        exercisesListAdapter = ExercisesListAdapter(exercisesList)
        recyclerView.adapter = exercisesListAdapter

        exercisesListViewModel = ViewModelProviders.of(this).get(ExercisesListViewModel::class.java)

        disposable = exercisesListViewModel.exercisesList.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe() {
            exercisesList.clear()
            exercisesList.addAll(it)
            exercisesListAdapter.notifyDataSetChanged()
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable?.dispose()
    }

}