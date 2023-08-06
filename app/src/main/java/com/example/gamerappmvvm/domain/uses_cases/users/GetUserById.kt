package com.example.gamerappmvvm.domain.uses_cases.users

import com.example.gamerappmvvm.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val repository: UsersRepository) {

    operator fun invoke(id: String) = repository.getUSerById(id)

}