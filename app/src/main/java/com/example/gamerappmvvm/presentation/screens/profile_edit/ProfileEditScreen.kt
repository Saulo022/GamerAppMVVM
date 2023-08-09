package com.example.gamerappmvvm.presentation.screens.profile_edit

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.gamerappmvvm.presentation.components.DefaultTopBar
import com.example.gamerappmvvm.presentation.screens.profile_edit.components.ProfileEditContent
import com.example.gamerappmvvm.presentation.screens.profile_edit.components.Update
import com.example.gamerappmvvm.presentation.screens.profile_edit.components.saveImage
import com.example.gamerappmvvm.presentation.screens.signup.components.SignupContent

@Composable
fun ProfileEditScreen(navController: NavHostController, user: String) {

    Log.d("ProfileEditScreen", "Usuario: $user ")

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar Usuario",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            ProfileEditContent(navController = navController)
        },
        bottomBar = {}
    )

    saveImage()
    Update()

}