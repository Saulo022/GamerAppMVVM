package com.example.gamerappmvvm.presentation.screens.profile

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerappmvvm.presentation.components.DefaultButton
import com.example.gamerappmvvm.presentation.navigation.AppScreen
import com.example.gamerappmvvm.presentation.screens.profile.components.ProfileContent

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {},
        content = {

            ProfileContent(navController)


        },
        bottomBar = {}
    )
}