package com.example.gamerappmvvm.domain.uses_cases.auth

import com.example.gamerappmvvm.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}