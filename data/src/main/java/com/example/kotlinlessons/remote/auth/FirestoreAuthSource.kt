package com.example.kotlinlessons.remote.auth

import com.example.kotlinlessons.model.ExequteResult
import com.example.kotlinlessons.model.User
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Single

class FirestoreAuthSource(private val firebaseAuth: FirebaseAuth) : IAuthSource {
    private val currentUser = firebaseAuth.currentUser

    override fun getCurrentUser(): Single<ExequteResult> {
        return Single.just(currentUser?.let { ExequteResult.Success(User(it.displayName ?: "", it.email ?: "", it.uid)) })
    }
}