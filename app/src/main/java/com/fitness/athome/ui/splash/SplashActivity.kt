package com.fitness.athome.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fitness.athome.App
import com.fitness.athome.BuildConfig
import com.fitness.athome.Constants.Companion.LOG_TAG
import com.fitness.athome.R
import com.fitness.athome.storage.PreferencesHelper
import com.fitness.athome.ui.BaseActivity
import com.fitness.athome.ui.auth.AuthActivity
import com.fitness.athome.ui.main.MainActivity
import com.fitness.athome.ui.user.data.EnterUserDataActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        App.appComponent.injectsSplashActivity(this@SplashActivity)

        supportActionBar?.hide()

        checkFirebaseUser()
    }

    fun checkFirebaseUser() {
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser;
        if (firebaseUser == null) {
            launchAuthActivity()
        } else {
            launchNextActivity()
        }

    }

    fun signInAnonymous() {
        firebaseAuth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    launchNextActivity()
                }
            }
    }

    fun launchAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun launchNextActivity() {
        if (preferencesHelper.isUserDataEnter() == true) {
            launchMainActivity()
        } else {
            //launchEnterUserDataActivity()
            launchMainActivity()
        }
    }

    fun launchMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun launchEnterUserDataActivity() {
        val intent = Intent(this, EnterUserDataActivity::class.java)
        startActivity(intent)
        finish()
    }

}