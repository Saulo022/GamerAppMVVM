package com.example.gamerappmvvm.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gamerappmvvm.presentation.screens.login.components.Login
import com.example.gamerappmvvm.presentation.screens.login.components.LoginBottomBar
import com.example.gamerappmvvm.presentation.screens.login.components.LoginContent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {

    Scaffold(
        topBar = {},
        content = { LoginContent(navController)},
        bottomBar = {
            LoginBottomBar(navController = navController)
        }
    )

    //MANEJAR EL ESTADO DE LA PETICION DE LOGIN
    Login(navController = navController)

}
