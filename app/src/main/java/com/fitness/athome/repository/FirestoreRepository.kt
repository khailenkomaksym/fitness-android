package com.fitness.athome.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class FirestoreRepository {

    var firestoreDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    var firebaseRC: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

}