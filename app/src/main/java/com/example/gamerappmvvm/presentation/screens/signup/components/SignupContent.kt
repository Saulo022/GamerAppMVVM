package com.example.gamerappmvvm.presentation.screens.signup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gamerappmvvm.R
import com.example.gamerappmvvm.presentation.components.DefaultButton
import com.example.gamerappmvvm.presentation.components.DefaultTextField
import com.example.gamerappmvvm.presentation.screens.signup.SignupViewModel
import com.example.gamerappmvvm.presentation.ui.theme.Red500


@Composable
fun SignupContent(navController: NavHostController, viewModel: SignupViewModel = hiltViewModel()) {

    val state = viewModel.state

    //val signupFlow = viewModel.signupFlow.collectAsState()

    Box(modifier = Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .background(Red500)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(80.dp))

                Image(
                    modifier = Modifier.height(110.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Imagen de Usuario"
                )

            }
        }

        Card(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 200.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {

                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    text = "REGISTRO",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Por favor rellena estos datos para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = state.username,
                    onValueChange = { viewModel.onUsernameInput(it) },
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.usernameErrMsg,
                    validateField = { viewModel.validateUsername() }
                )


                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.email,
                    onValueChange = { viewModel.onEmailInput(it) },
                    label = "Correo electronico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg,
                    validateField = { viewModel.validateEmail() }
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.password,
                    onValueChange = { viewModel.onPasswordInput(it) },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg,
                    validateField = { viewModel.validatePassword() }
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.confirmPassword,
                    onValueChange = { viewModel.onConfirmPasswordInput(it) },
                    label = "Confirmar contraseña",
                    icon = Icons.Outlined.Lock,
                    hideText = true,
                    errorMsg = viewModel.confirmPasswordErrMsg,
                    validateField = { viewModel.validateConfirmPassword() }
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 35.dp),
                    text = "REGISTRARSE",
                    onClick = { viewModel.onSignup() },
                    enabled = viewModel.isEnabledLoginButton
                )


            }
        }
    }

}




