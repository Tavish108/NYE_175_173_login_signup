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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.layout.ContentScale
import com.example.nye_175_173_login_signup.R

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel: AuthViewModel = viewModel()
    val context = LocalContext.current
    val message by viewModel.message.collectAsState()

    LaunchedEffect(message) {
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            if (it.contains("Login successful", ignoreCase = true)) {
                navController.navigate("mainapp") {
                    popUpTo("login") { inclusive = true }
                }
            }
            viewModel.clearMessages()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_img),
            contentDescription = stringResource(R.string.login_image_desc),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.login_title),
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF4C1159)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(R.string.email)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (username.isBlank() || password.isBlank()) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.enter_all_fields),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.login(email = username.trim(), password = password.trim())
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4C1159),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.login_button))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.signup_link),
            textAlign = TextAlign.Center,
            color = Color(0xFF4C1159),
            modifier = Modifier
                .clickable { navController.navigate("signup") }
                .padding(8.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

//above is old


//
//package com.example.nye_175_173_login_signup.screen.login
//
//import android.app.Activity
//import android.content.Context
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.example.nye_175_173_login_signup.R
//import com.example.nye_175_173_login_signup.util.LanguageOptions
//import com.example.nye_175_173_login_signup.util.LocaleUtils
//import com.example.nye_175_173_login_signup.viewmodel.AuthViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LoginScreen(navController: NavController) {
//    var username by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    val viewModel: AuthViewModel = viewModel()
//    val context = LocalContext.current
//    val activity = context as? Activity
//    val message by viewModel.message.collectAsState()
//
//    var languageMenuExpanded by remember { mutableStateOf(false) }
//
//    LaunchedEffect(message) {
//        message?.let {
//            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
//            if (it.contains("Login successful", ignoreCase = true)) {
//                navController.navigate("mainapp") {
//                    popUpTo("login") { inclusive = true }
//                }
//            }
//            viewModel.clearMessages()
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(stringResource(R.string.login_title)) },
//                actions = {
//                    Box {
//                        IconButton(onClick = { languageMenuExpanded = true }) {
//                            Icon(Icons.Default.Settings, contentDescription = "Change Language")
//                        }
//                        DropdownMenu(
//                            expanded = languageMenuExpanded,
//                            onDismissRequest = { languageMenuExpanded = false }
//                        ) {
//                            LanguageOptions.values().forEach { language ->
//                                DropdownMenuItem(
//                                    text = { Text(stringResource(id = language.displayNameResId)) },
//                                    onClick = {
//                                        changeLanguage(context, activity, language.code)
//                                        languageMenuExpanded = false
//                                    }
//                                )
//                            }
//                        }
//                    }
//                }
//            )
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .padding(32.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.login_img),
//                contentDescription = stringResource(R.string.login_image_desc),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp),
//                contentScale = ContentScale.Crop
//            )
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            OutlinedTextField(
//                value = username,
//                onValueChange = { username = it },
//                label = { Text(stringResource(R.string.email)) },
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                label = { Text(stringResource(R.string.password)) },
//                visualTransformation = PasswordVisualTransformation(),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Button(
//                onClick = {
//                    if (username.isBlank() || password.isBlank()) {
//                        Toast.makeText(
//                            context,
//                            context.getString(R.string.enter_all_fields),
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    } else {
//                        viewModel.login(email = username.trim(), password = password.trim())
//                    }
//                },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF4C1159),
//                    contentColor = Color.White
//                ),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(stringResource(R.string.login_button))
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Text(
//                text = stringResource(R.string.signup_link),
//                textAlign = TextAlign.Center,
//                color = Color(0xFF4C1159),
//                modifier = Modifier
//                    .clickable { navController.navigate("signup") }
//                    .padding(8.dp),
//                style = MaterialTheme.typography.bodySmall
//            )
//        }
//    }
//}
//
//private fun changeLanguage(context: Context, activity: Activity?, languageCode: String) {
//    val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
//    prefs.edit().putString("language", languageCode).apply()
//    LocaleUtils.setLocale(context, languageCode)
//    activity?.recreate()
//}
