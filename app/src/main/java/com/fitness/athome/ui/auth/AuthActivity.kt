package com.fitness.athome.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.fitness.athome.App
import com.fitness.athome.Constants.Companion.LOG_TAG
import com.fitness.athome.Constants.Companion.RC_SIGN_IN
import com.fitness.athome.R
import com.fitness.athome.model.user_data.UserData
import com.fitness.athome.repository.FirebaseAuthRepository
import com.fitness.athome.repository.FirestoreRepository
import com.fitness.athome.ui.BaseActivity
import com.fitness.athome.ui.main.MainActivity
import com.fitness.athome.ui.user.data.EnterUserDataActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import javax.inject.Inject

class AuthActivity : BaseActivity() {

    lateinit var toolbar: Toolbar
    lateinit var btnSignInFacebook: MaterialButton
    lateinit var btnSignInGoogle: MaterialButton
    lateinit var btnContinueAsGuest: MaterialButton

    lateinit var callbackManager: CallbackManager

    lateinit var googleSignInClient: GoogleSignInClient

    lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var firebaseAuthRepository: FirebaseAuthRepository

    @Inject
    lateinit var firestoreRepository: FirestoreRepository

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    firebaseAuthWithFacebook(loginResult.accessToken)
                }

                override fun onCancel() {
                }

                override fun onError(exception: FacebookException) {
                }
            })

        setContentView(R.layout.activity_auth)

        App.appComponent.injectsAuthActivity(this)

        toolbar = findViewById(R.id.toolbar)
        setToolbar()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        authViewModel = ViewModelProviders.of(this, AuthViewModelFactory(firebaseAuthRepository, firestoreRepository)).get(AuthViewModel::class.java)

        btnSignInFacebook = findViewById(R.id.btnSignInFacebook)
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle)
        btnContinueAsGuest = findViewById(R.id.btnContinueAsGuest)

        authViewModel.authTask.observe(this, Observer<Task<AuthResult>> {
            if (it.isSuccessful) {
                checkFirestoreUserData()
            } else {
                hideProgressDialog()
                Toast.makeText(this@AuthActivity, "Error: " + it?.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        btnSignInFacebook.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this@AuthActivity, Arrays.asList("email", "public_profile"))

        }

        btnSignInGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        btnContinueAsGuest.setOnClickListener {
            firebaseAuthWithAnonymous()
        }
    }

    fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.auth_title)
    }

    fun firebaseAuthWithFacebook(accessToken: AccessToken) {
        showProgressDialog()
        authViewModel.firebaseAuthWithFacebook(accessToken)
    }

    fun firebaseAuthWithGoogle(googleSignInAccount: GoogleSignInAccount) {
        showProgressDialog()
        authViewModel.firebaseAuthWithGoogle(googleSignInAccount)
    }

    fun firebaseAuthWithAnonymous() {
        showProgressDialog()
        authViewModel.firebaseAuthWithAnonymous()
    }

    fun checkFirestoreUserData() {
        firebaseAuth.currentUser.let {
            it?.let {
                authViewModel.getUserDataInfo(it.uid).observe(this, Observer<UserData> {
                    hideProgressDialog()
                    if (it == null) {
                        launchUserDataActivity()
                    } else {
                        launchMainActivity()
                    }
                })
            }
        }
    }

    fun launchUserDataActivity() {
        val intent = Intent(this, EnterUserDataActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun launchMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                Log.w(LOG_TAG, "Google sign in failed", e)
            }
        }
    }

}