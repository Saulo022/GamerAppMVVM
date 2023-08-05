package com.example.gamerappmvvm.domain.repository

import com.example.gamerappmvvm.domain.model.Response
import com.example.gamerappmvvm.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun signUp(user: User): Response<FirebaseUser>
    fun logout()
}