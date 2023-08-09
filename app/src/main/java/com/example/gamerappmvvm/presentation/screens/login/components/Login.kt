package com.example.gamerappmvvm.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerappmvvm.domain.model.Response
import com.example.gamerappmvvm.presentation.components.ProgressBar
import com.example.gamerappmvvm.presentation.navigation.Graph
import com.example.gamerappmvvm.presentation.screens.login.LoginViewModel

@Composable
fun Login(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    when(val loginResponse = viewModel.loginResponse) {

        //MOSTRAR QUE SE ESTA REALIZANDO LA PETICION Y TODAVIA ESTA EN PROCESO
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(route = Graph.HOME) {
                    //ESTO ES PARA QUE UNA VEZ INICIEMOS SESION BORRE ESTA PANTALLA
                    // DE LA PILA DE PANTALLAS ANTERIORES

                    //BORRAMOS DIRECTAMENTE EL GRAFO DE AUTENTICACION
                    popUpTo(Graph.AUTHENTICATION) { inclusive = true }
                }

            }
        }

        is Response.Failure -> {

            Toast.makeText(LocalContext.current, loginResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }

}