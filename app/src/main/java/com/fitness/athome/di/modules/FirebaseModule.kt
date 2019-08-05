package com.fitness.athome.di.modules

import com.fitness.athome.repository.FirebaseAuthRepository
import com.fitness.athome.repository.FirestoreRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {

    @Provides
    internal fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    internal fun provideFirebaseAuthRepository(firebaseAuth: FirebaseAuth): FirebaseAuthRepository {
        return FirebaseAuthRepository(firebaseAuth)
    }

    @Provides
    internal fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    internal fun provideFirestoreRepository(firestore: FirebaseFirestore): FirestoreRepository {
        return FirestoreRepository(firestore)
    }

}