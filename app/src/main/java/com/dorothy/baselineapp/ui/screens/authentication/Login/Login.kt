package com.dorothy.baselineapp.ui.screens.authentication.Login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dorothy.baselineapp.ui.screens.authentication.register.viewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = viewModel(),

    modifier: Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}
