package com.uiel.scul

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uiel.scul.feature.login.LoginScreen
import com.uiel.scul.feature.login.LoginViewModel

@Composable
fun SculApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login",
    ) {
        composable("login") {
            LoginScreen(
                navController = navController,
            )
        }
        composable("home") {

        }
    }
}
