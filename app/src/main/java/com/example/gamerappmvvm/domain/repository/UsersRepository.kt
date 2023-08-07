package com.example.gamerappmvvm.domain.repository

import com.example.gamerappmvvm.domain.model.Response
import com.example.gamerappmvvm.domain.model.User
import kotlinx.coroutines.flow.Flow


interface UsersRepository {

    suspend fun create(user: User): Response<Boolean>
    suspend fun update(user: User): Response<Boolean>
    fun getUSerById(id: String): Flow<User>
}