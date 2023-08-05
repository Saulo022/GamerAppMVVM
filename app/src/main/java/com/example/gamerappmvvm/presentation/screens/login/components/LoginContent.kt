package com.example.gamerappmvvm.presentation.screens.login.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerappmvvm.R
import com.example.gamerappmvvm.domain.model.Response
import com.example.gamerappmvvm.presentation.components.DefaultButton
import com.example.gamerappmvvm.presentation.components.DefaultTextField
import com.example.gamerappmvvm.presentation.navigation.AppScreen
import com.example.gamerappmvvm.presentation.screens.login.LoginViewModel
import com.example.gamerappmvvm.presentation.ui.theme.Red500


@Composable
fun LoginContent(navController: NavHostController,viewModel: LoginViewModel = hiltViewModel()) {

    val loginFlow = viewModel.loginFlow.collectAsState()

    Box(modifier = Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Red500)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier.height(130.dp),
                    painter = painterResource(id = R.drawable.control),
                    contentDescription = "Control de xbox 360"
                )

                Text(text = "FIREBASE MVVM")

            }
        }

        Card(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 200.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {

                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Por favor inicia sesión para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )


                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = viewModel.email.value,
                    onValueChange = { viewModel.email.value = it },
                    label = "Correo Electronico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg.value,
                    validateField = {
                        viewModel.validateEmail()
                    }
                )


                DefaultTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = viewModel.password.value,
                    onValueChange = { viewModel.password.value = it },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg.value,
                    validateField = {
                        viewModel.validatePassword()
                    }
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 35.dp),
                    text = "INICIAR SESION",
                    onClick = {
                        viewModel.login()
                    },
                    enabled = viewModel.isEnabledLoginButton
                )


            }
        }
    }

    loginFlow.value.let {
        when(it) {

            //MOSTRAR QUE SE ESTA REALIZANDO LA PETICION Y TODAVIA ESTA EN PROCESO
            Response.Loading -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            }
            is Response.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(route = AppScreen.Profile.route)
                }
                Toast.makeText(LocalContext.current, "Usuario Logueado", Toast.LENGTH_LONG).show()
            }

            is Response.Failure -> {

                Toast.makeText(LocalContext.current, it.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
            }

            else -> {}
        }
    }
}

