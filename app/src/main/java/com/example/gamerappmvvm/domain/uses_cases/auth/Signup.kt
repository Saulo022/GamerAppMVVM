package com.example.gamerappmvvm.domain.uses_cases.auth

import com.example.gamerappmvvm.domain.model.User
import com.example.gamerappmvvm.domain.repository.AuthRepository
import javax.inject.Inject

class Signup @Inject constructor(private val  repository: AuthRepository) {

    suspend operator fun invoke(user: User) = repository.signUp(user)
}