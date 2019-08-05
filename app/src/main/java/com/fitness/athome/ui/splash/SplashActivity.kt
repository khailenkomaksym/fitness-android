package com.fitness.athome.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fitness.athome.App
import com.fitness.athome.R
import com.fitness.athome.model.user_data.UserData
import com.fitness.athome.repository.FirestoreRepository
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

    @Inject
    lateinit var firestoreRepository: FirestoreRepository

    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        App.appComponent.injectsSplashActivity(this@SplashActivity)

        supportActionBar?.hide()

        splashViewModel = ViewModelProviders.of(this, SplashViewModelFactory(firestoreRepository)).get(SplashViewModel::class.java)

        checkFirebaseUser()
    }

    fun checkFirebaseUser() {
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser;
        if (firebaseUser == null) {
            launchAuthActivity()
        } else {
            checkFirestoreUserData()
        }

    }

    fun launchAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun checkFirestoreUserData() {
        firebaseAuth.currentUser.let {
            it?.let {
                splashViewModel.getUserDataInfo(it.uid).observe(this, Observer<UserData> {
                    hideProgressDialog()
                    if (it == null) {
                        launchEnterUserDataActivity()
                    } else {
                        launchMainActivity()
                    }
                })
            }
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