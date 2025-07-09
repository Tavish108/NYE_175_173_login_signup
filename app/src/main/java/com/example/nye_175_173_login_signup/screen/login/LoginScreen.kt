package com.example.nye_175_173_login_signup.screen.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nye_175_173_login_signup.viewmodel.AuthViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.nye_175_173_login_signup.R

@Composable
fun LoginScreen(navController: NavController) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val viewModel: AuthViewModel = viewModel()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Background Image (resized)
        Image(
            painter = painterResource(id = R.drawable.login_img),
            contentDescription = "Login Top Banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), // Resize height here
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Login",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF4C1159)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (username.isBlank() || password.isBlank()) {
                    Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.login(
                        email = username.trim(),
                        password = password.trim()
                    ) { success, errorMessage ->
                        if (success) {
                            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                            navController.navigate("mainapp") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                errorMessage ?: "Login failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4C1159),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Don't have an account? Sign up",
            textAlign = TextAlign.Center,
            color = Color(0xFF4C1159),
            modifier = Modifier
                .clickable {
                    navController.navigate("signup")
                }
                .padding(8.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
