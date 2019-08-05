package com.fitness.athome.ui.main

import android.app.ProgressDialog.show
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.fitness.athome.App
import com.fitness.athome.R
import com.fitness.athome.storage.PreferencesHelper
import com.fitness.athome.ui.BaseActivity
import com.fitness.athome.ui.dashboard.DashboardFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        p0.isChecked = true
        when (p0.itemId) {
            R.id.nav_dashboard -> {
                val dashboardFragment = DashboardFragment()
                replaceFragment(dashboardFragment)
            }
            R.id.nav_exercises -> {
                //Toast.makeText(this@MainActivity, "Menu2: " + p0.order, Toast.LENGTH_LONG).show()
            }
            else -> {
                //Toast.makeText(this@MainActivity, "Menu3: " + p0.order, Toast.LENGTH_LONG).show()
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

        /*runBlocking {
            main()
        }*/
    }

    /*suspend fun main() = coroutineScope {
        launch {
            delay(1000)
            println("Kotlin Coroutines World!")
        }
        println("Hello")
    }

    fun onDocsNeeded() {
        viewModelScope.launch {    // Dispatchers.Main
            main()            // Dispatchers.Main (suspend function call)
        }
    }*/

    suspend fun get(url: String) = withContext(Dispatchers.IO) {
        Log.d("myLogs", "test")
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        fragmentTransaction.replace(R.id.containerView, fragment, "fragment_tag")
        fragmentTransaction.commit()
    }

}