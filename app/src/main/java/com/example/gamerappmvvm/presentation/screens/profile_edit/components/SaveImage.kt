package com.example.gamerappmvvm.presentation.screens.profile_edit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamerappmvvm.domain.model.Response
import com.example.gamerappmvvm.presentation.components.ProgressBar
import com.example.gamerappmvvm.presentation.screens.profile_edit.ProfileEditViewModel

@Composable
fun saveImage(viewModel: ProfileEditViewModel = hiltViewModel()) {

    when(val response = viewModel.saveImageResponse) {
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            viewModel.onUpdate(response.data)
        }

        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }
}