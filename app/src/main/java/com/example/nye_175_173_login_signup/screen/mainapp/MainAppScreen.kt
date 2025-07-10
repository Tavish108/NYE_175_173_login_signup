@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.nye_175_173_login_signup.screen.mainapp

import com.example.nye_175_173_login_signup.util.LanguageOptions

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nye_175_173_login_signup.R
import com.example.nye_175_173_login_signup.util.LocaleUtils


@ExperimentalMaterial3Api
@Composable
fun MainAppScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? Activity
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    Box {

                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.Settings, contentDescription = "Change Language")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("English") },
                                onClick = {
                                    changeLanguage(context, activity, "en")
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("हिंदी") },
                                onClick = {
                                    changeLanguage(context, activity, "hi")
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.login_title), // "Welcome to Main App!"
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate("login") {
                            popUpTo("mainapp") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4C1159),
                        contentColor = Color.White
                    )
                ) {
                    Text(stringResource(R.string.login_button)) // "Logout"
                }
            }
        }
    }
}

private fun changeLanguage(context: Context, activity: Activity?, languageCode: String) {
    val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    prefs.edit().putString("language", languageCode).apply()
    LocaleUtils.setLocale(context, languageCode)
    activity?.recreate()
}
