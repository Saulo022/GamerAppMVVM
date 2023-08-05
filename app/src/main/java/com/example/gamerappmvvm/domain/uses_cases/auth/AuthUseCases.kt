package com.example.gamerappmvvm.domain.uses_cases.auth

data class AuthUseCases(
    val getCurrentUSer: GetCurrentUSer,
    val login: Login,
    val logout: Logout
)