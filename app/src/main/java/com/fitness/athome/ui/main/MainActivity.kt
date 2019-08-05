package com.fitness.athome.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.fitness.athome.App
import com.fitness.athome.R
import com.fitness.athome.storage.PreferencesHelper
import com.fitness.athome.ui.BaseActivity
import com.fitness.athome.ui.dashboard.DashboardFragment
import com.fitness.athome.ui.exercises.ExercisesListFragment
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        p0.isChecked = true
        when (p0.itemId) {
            R.id.nav_dashboard -> {
                setToolbarTitle(getString(R.string.nav_dashboard))
                val dashboardFragment = DashboardFragment()
                replaceFragment(dashboardFragment)
            }
            R.id.nav_exercises -> {
                setToolbarTitle(getString(R.string.nav_exercises))
                val exercisesListFragment = ExercisesListFragment()
                replaceFragment(exercisesListFragment)
            }
            else -> {
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true;
    }

    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var navigationView: NavigationView

    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.appComponent.injectsMainActivity(this@MainActivity)

        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.nav_view)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        onNavigationItemSelected(navigationView.menu.getItem(0))
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        fragmentTransaction.replace(R.id.containerView, fragment, "fragment_tag")
        fragmentTransaction.commit()
    }

}