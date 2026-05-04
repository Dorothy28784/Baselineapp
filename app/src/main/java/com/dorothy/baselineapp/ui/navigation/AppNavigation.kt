package com.dorothy.baselineapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dorothy.baselineapp.ui.screens.authentication.forgotpassword.ForgotPasswordScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier
){
    NavHost(
        navController = navController,
        startDestination = ROUTES.Register.name
    ){
        composable(ROUTES.Login.name) { LoginScreen( navController= navController,modifier = modifier) }
        composable(ROUTES.ForgotPassword.name) { ForgotPasswordScreen( navController= navController,modifier = modifier) }
        composable(ROUTES.Register.name) { RegisterScreen( navController= navController,modifier = modifier) }
        composable(ROUTES.Home.name) {LoginScreen( navController= navController,modifier = modifier) }
    }
}