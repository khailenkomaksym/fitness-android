package com.fitness.athome.ui.user.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.athome.model.user_data.UserData
import com.fitness.athome.repository.FirestoreRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference

class EnterUserDataViewModel(private val firestoreRepository: FirestoreRepository) : ViewModel() {

    var docTask: MutableLiveData<Task<Void>> = MutableLiveData()

    fun insertUserData(uid: String, userData: UserData) {
        firestoreRepository.insertUserDataInfo(uid, userData).addOnCompleteListener {
            task -> docTask.postValue(task)
        }
    }

}