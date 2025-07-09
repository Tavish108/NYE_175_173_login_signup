package com.example.nye_175_173_login_signup.screen.mainapp


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainAppScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Welcome to Main App!",
                style = MaterialTheme.typography.headlineMedium

            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                // Handle logout or navigate somewhere else
                navController.navigate("login") {
                    popUpTo("mainapp") { inclusive = true }
                }
            },colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4C1159),  // Purple background
                contentColor = Color.White           // White text
            ),)

            {
                Text("Logout")
            }
        }
    }
}
