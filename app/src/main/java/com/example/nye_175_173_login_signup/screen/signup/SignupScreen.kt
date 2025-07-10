package com.example.nye_175_173_login_signup.screen.signup
import androidx.compose.ui.res.stringResource


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.nye_175_173_login_signup.R
import com.example.nye_175_173_login_signup.viewmodel.AuthViewModel

@Composable
fun SignupScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    val message by viewModel.message.collectAsState()

    LaunchedEffect(message) {
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            if (it.contains("Signup successful", ignoreCase = true)) {
                navController.navigate("login") {
                    popUpTo("signup") { inclusive = true }
                }
            }
            viewModel.clearMessages()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.signup_title), style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(stringResource(R.string.confirm_password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        if (error != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = error ?: "", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                error = when {
                    email.isBlank() || password.isBlank() || confirmPassword.isBlank() ->
                        context.getString(R.string.please_fill_all_fields)
                    password != confirmPassword ->
                        context.getString(R.string.passwords_do_not_match)
                    else -> {
                        viewModel.signup(email.trim(), password.trim())
                        null
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4C1159),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.signup_button))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.login_link),
            modifier = Modifier
                .clickable {
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
                .padding(top = 8.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
