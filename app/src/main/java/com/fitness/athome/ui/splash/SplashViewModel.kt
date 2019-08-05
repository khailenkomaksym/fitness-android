package com.fitness.athome.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.athome.Constants
import com.fitness.athome.model.user_data.UserData
import com.fitness.athome.repository.FirestoreRepository
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener

class SplashViewModel(private val firestoreRepository: FirestoreRepository) : ViewModel() {

    var userDataInfo : MutableLiveData<UserData> = MutableLiveData()

    fun getUserDataInfo(uid: String): LiveData<UserData> {
        firestoreRepository.getUserDataInfo(uid).addSnapshotListener(EventListener<DocumentSnapshot> { value, e ->
            if (e != null) {
                Log.w(Constants.LOG_TAG, "Listen failed.", e)
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

}