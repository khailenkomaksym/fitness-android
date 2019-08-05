package com.fitness.athome.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.facebook.internal.Mutable
import com.fitness.athome.Constants.Companion.LOG_TAG
import com.fitness.athome.model.user_data.UserData
import com.fitness.athome.repository.FirebaseAuthRepository
import com.fitness.athome.repository.FirestoreRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class AuthViewModel(val firebaseAuthRepository: FirebaseAuthRepository, val firestoreRepository: FirestoreRepository) : ViewModel() {

    var authTask: MutableLiveData<Task<AuthResult>> = MutableLiveData()

    var userDataInfo : MutableLiveData<UserData> = MutableLiveData()

    fun getUserDataInfo(uid: String): LiveData<UserData> {
        firestoreRepository.getUserDataInfo(uid).addSnapshotListener(EventListener<DocumentSnapshot> { value, e ->
            if (e != null) {
                Log.w(LOG_TAG, "Listen failed.", e)
                userDataInfo.value = null
                return@EventListener
            }

            if (value != null) {
                userDataInfo.value = value.toObject(UserData::class.java)
            } else {
                userDataInfo.value = null
            }
        })

        return userDataInfo
    }

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