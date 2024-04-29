package com.uiel.scul.feature.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    val event = viewModel.event
    
    LaunchedEffect(event) {
        event.collect{
            when(it) {
                is LoginViewModel.Event.NavigateToMain -> {
                    navController.navigate("home")
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = "",
            onValueChange = {},
        )
    }
}