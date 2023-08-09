package com.example.gamerappmvvm.presentation.screens.posts

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun PostsScreen(navController: NavHostController) {
    
    Scaffold(
        content = {
            Text(text = "PostsScreen")
        }
    ) 
    
}