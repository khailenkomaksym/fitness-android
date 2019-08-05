package com.fitness.athome.repository

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class FirebaseAuthRepository(private val firebaseAuth: FirebaseAuth) {

    fun firebaseAuthWithFacebook(token: AccessToken): Task<AuthResult> {
        val credential = FacebookAuthProvider.getCredential(token.token)
        return firebaseAuth.signInWithCredential(credential)
    }

    fun firebaseAuthWithGoogle(googleSignInAccount: GoogleSignInAccount): Task<AuthResult> {
        val credential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
        return firebaseAuth.signInWithCredential(credential)
    }

    fun firebaseAuthWithAnonymous(): Task<AuthResult> {
        return firebaseAuth.signInAnonymously()
    }

    fun logout() = firebaseAuth.signOut()

}