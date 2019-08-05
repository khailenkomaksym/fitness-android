package com.fitness.athome.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fitness.athome.Constants.Companion.LOG_TAG
import com.fitness.athome.R
import com.fitness.athome.db.exercises.Exercises
import com.fitness.athome.db.exercises.ExercisesDatabase
import com.fitness.athome.model.Workout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var cardViewWater: CardView

    private var dashboardViewModel: DashboardViewModel? = null

    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View =  inflater.inflate(R.layout.fragment_dashboard, container, false)

        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        //dashboardViewModel = ViewModelProviders.of(this)[DashboardViewModel::class.java]

        disposable = dashboardViewModel?.exercisesList?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe() {
            val exercisesList: List<Exercises> = it
            Log.d(LOG_TAG, "arch components: " + exercisesList.size)
            Log.d(LOG_TAG, "arch components: " + exercisesList.get(0).title)
        }

        var workout: Workout = Workout(Date().time)

        /*dashboardViewModel?.exercisesList?.observe(this, object: Observer<List<Exercises>> {
            override fun onChanged(exercisesList: List<Exercises>?) {
                Log.d(LOG_TAG, "arch components: " + exercisesList?.size)
                Log.d(LOG_TAG, "arch components: " + exercisesList?.get(0)?.title)
            }
        })*/

        /*var exercisesDatabase: ExercisesDatabase? = activity?.applicationContext?.let { ExercisesDatabase.getInstance(it) }

        val exercisesList: List<Exercises> = exercisesDatabase?.exercisesDao()?.getAllExercises()!!

        Toast.makeText(context, "Size: " + exercisesList.get(0).title, Toast.LENGTH_LONG).show()*/


        cardViewWater = view.findViewById(R.id.cardWater)
        cardViewWater.setOnClickListener {

        }

        return view;
    }

    override fun onStart() {
        super.onStart()

        //disposable.add()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable?.dispose()

        //disposable.clear()
    }

}