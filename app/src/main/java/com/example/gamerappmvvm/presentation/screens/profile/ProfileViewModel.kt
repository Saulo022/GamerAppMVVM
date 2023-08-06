package com.example.gamerappmvvm.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamerappmvvm.domain.model.User
import com.example.gamerappmvvm.domain.uses_cases.auth.AuthUseCases
import com.example.gamerappmvvm.domain.uses_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCases: UsersUseCases
) : ViewModel() {

    var userData by mutableStateOf(User())
        private set
    val currentUser = authUseCases.getCurrentUser()

    init {
        getUserById()
    }
    private fun getUserById() {
        viewModelScope.launch {
            usersUseCases.getUserById(currentUser!!.uid).collect() {
                userData = it
            }
        }
    }

    fun logout() {
        authUseCases.logout()
    }
}