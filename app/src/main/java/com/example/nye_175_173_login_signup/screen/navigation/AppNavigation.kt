package com.example.nye_175_173_login_signup.screen.navigation



import androidx.navigation.compose.*
import com.example.nye_175_173_login_signup.screen.splash.SplashScreen
import com.example.nye_175_173_login_signup.screen.login.LoginScreen
import com.example.nye_175_173_login_signup.screen.signup.SignupScreen
import com.example.nye_175_173_login_signup.screen.mainapp.MainAppScreen

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable

// Add SignupScreen if needed

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("signup") {
            SignupScreen(navController)
        }
        composable("mainapp") { MainAppScreen(navController) }
        // You can add more like:
        // composable("signup") { SignupScreen(navController) }
    }
}
