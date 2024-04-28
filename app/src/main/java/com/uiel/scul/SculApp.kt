package com.uiel.scul

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun SculApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "",
    ) {

    }
}
