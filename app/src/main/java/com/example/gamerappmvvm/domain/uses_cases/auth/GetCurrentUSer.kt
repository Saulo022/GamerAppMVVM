package com.example.gamerappmvvm.domain.uses_cases.auth

import com.example.gamerappmvvm.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUSer @Inject constructor(private val repository: AuthRepository){

    operator fun invoke() = repository.currentUser
}