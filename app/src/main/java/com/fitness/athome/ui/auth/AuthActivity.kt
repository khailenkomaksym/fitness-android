package com.fitness.athome.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fitness.athome.Constants.Companion.LOG_TAG
import com.fitness.athome.Constants.Companion.RC_SIGN_IN
import com.fitness.athome.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.button.MaterialButton

class AuthActivity : AppCompatActivity() {

    lateinit var btnSignInFacebook: MaterialButton
    lateinit var btnSignInGoogle: MaterialButton
    lateinit var btnContinueAsGuest: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        btnSignInFacebook = findViewById(R.id.btnSignInFacebook)
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle)
        btnContinueAsGuest = findViewById(R.id.btnContinueAsGuest)

        btnSignInFacebook.setOnClickListener {
            handleFacebookSignIn()
        }

        btnSignInGoogle.setOnClickListener {
            handleGoogleSignIn()
        }

        btnContinueAsGuest.setOnClickListener {
            handleAnonymousSignIn()
        }
    }

    fun handleFacebookSignIn() {
        showProgressBar()
    }

    fun handleGoogleSignIn() {
        showProgressBar()
    }

    fun handleAnonymousSignIn() {
        showProgressBar()
    }

    fun showProgressBar() {

    }

    fun hideProgressBar() {

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                //firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(LOG_TAG, "Google sign in failed", e)
                // ...
            }
        }
    }

}