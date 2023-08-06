package com.example.gamerappmvvm.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamerappmvvm.domain.model.Response
import com.example.gamerappmvvm.domain.uses_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    //STATE FORM
    var state by mutableStateOf(LoginState())
        private set

    //EMAIL
    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrMsg by mutableStateOf("")
        private set

    //PASSWORD
    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")
        private set

    //BUTTON
    var isEnabledLoginButton = false

    /*private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow*/

    //LOGIN RESPONSE
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)


    val currentUser = authUseCases.getCurrentUser()

    init {
        if (currentUser != null) { //SESION INICIADA
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun login() {
        viewModelScope.launch {
            loginResponse = Response.Loading
            val result = authUseCases.login(state.email, state.password)
            loginResponse = result
        }
    }

    fun enabledLoginButton() {
        isEnabledLoginButton = isEmailValid && isPasswordValid
    }

    fun validateEmail() {

        //ES UN EMAIL VALIDO
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrMsg = ""
        } else {
            isEmailValid = false
            emailErrMsg = "El email no es valido"
        }

        enabledLoginButton()
    }


    fun validatePassword() {
        if (state.password.length >= 6) {
            isPasswordValid = true
            passwordErrMsg = ""
        } else {
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 caracteres"
        }

        enabledLoginButton()
    }

}