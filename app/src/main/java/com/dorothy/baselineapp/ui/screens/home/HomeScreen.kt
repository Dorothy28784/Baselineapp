package com.dorothy.baselineapp.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dorothy.baselineapp.ui.navigation.ROUTES

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome!",
                style = MaterialTheme.typography.displaySmall
            )

            uiState.userEmail?.let { email ->
                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "This is the Home Screen",
                style = MaterialTheme.typography.bodyLarge
            )

            Button(
                onClick = {
                    viewModel.logout()
                    navController.navigate(ROUTES.Login.name) {
                        popUpTo(ROUTES.Home.name) { inclusive = true }
                    }
                }
            ) {
                Text("Sign Out")
            }
        }

        Button(
            onClick = {
                navController.navigate("add")
            }
        ){
            Text("Add reading")
        }
    }
}

