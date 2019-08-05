package com.fitness.athome.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.facebook.internal.Mutable
import com.fitness.athome.repository.FirebaseAuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class AuthViewModel(val firebaseAuthRepository: FirebaseAuthRepository) : ViewModel() {

    //lateinit var isShowProgressBar: MutableLiveData<Boolean>
    var authTask: MutableLiveData<Task<AuthResult>> = MutableLiveData()

    fun firebaseAuthWithAnonymous() {
        firebaseAuthRepository.firebaseAuthWithAnonymous().addOnCompleteListener {
            task -> authTask.postValue(task)
        }
    }

    fun firebaseAuthWithFacebook(accessToken: AccessToken) {
        firebaseAuthRepository.firebaseAuthWithFacebook(accessToken).addOnCompleteListener {
                task -> authTask.postValue(task)
        }
    }

    fun firebaseAuthWithGoogle(googleSignInAccount: GoogleSignInAccount) {
        firebaseAuthRepository.firebaseAuthWithGoogle(googleSignInAccount).addOnCompleteListener {
                task -> authTask.postValue(task)
        }
    }

}