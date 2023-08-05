package com.example.gamerappmvvm.domain.repository

import com.example.gamerappmvvm.domain.model.Response
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    fun logout()
}