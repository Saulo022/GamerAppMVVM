package com.example.gamerappmvvm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gamerappmvvm.presentation.screens.home.HomeScreen
import com.example.gamerappmvvm.presentation.screens.login.LoginScreen
import com.example.gamerappmvvm.presentation.screens.profile.ProfileScreen
import com.example.gamerappmvvm.presentation.screens.profile_edit.ProfileEditScreen
import com.example.gamerappmvvm.presentation.screens.signup.SignupScreen

@Composable
fun RootNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {

        authNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            HomeScreen()
        }

    }
}

