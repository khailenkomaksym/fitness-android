package com.fitness.athome.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fitness.athome.model.user_data.UserData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class FirestoreRepository(private val firestore: FirebaseFirestore) {

    fun getUserDataInfo(uid: String): DocumentReference {
        val collectionReference = firestore.collection(USER_DATA_COLLECTION).document(uid)
        return collectionReference
    }

    fun insertUserDataInfo(uid: String, userData: UserData) : Task<Void> {
        return firestore.collection(USER_DATA_COLLECTION).document(uid).set(userData)
    }

    companion object {
        val USER_DATA_COLLECTION: String = "user_data"
    }

}