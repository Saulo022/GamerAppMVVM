package com.example.gamerappmvvm.domain.uses_cases.users

import com.example.gamerappmvvm.domain.model.User
import com.example.gamerappmvvm.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(user: User) = repository.create(user)
}