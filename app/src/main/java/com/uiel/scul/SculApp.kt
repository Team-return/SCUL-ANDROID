package com.uiel.scul

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.uiel.scul.feature.detail.DetailScreen
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
        composable("main") {
            BottomNavigationBar(navController = navController)
        }
        composable(
            route = "detail/{cultureId}",
            arguments = listOf(navArgument("cultureId") {
                type = NavType.StringType
            })
        ) {
            DetailScreen(
                cultureId = it.arguments?.getString("cultureId") ?: "",
                navController = navController,
            )
        }
    }
}
