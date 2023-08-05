package com.example.gamerappmvvm.presentation.screens.signup

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gamerappmvvm.presentation.components.DefaultTopBar
import com.example.gamerappmvvm.presentation.screens.signup.components.SignupContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignupScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo Usuario",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            SignupContent(navController)
        },
        bottomBar = {}
    )
}