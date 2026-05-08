package com.dorothy.baselineapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dorothy.baselineapp.ui.screens.authentication.Login.LoginScreen
import com.dorothy.baselineapp.ui.screens.authentication.forgotpassword.ForgotPasswordScreen
import com.dorothy.baselineapp.ui.screens.authentication.register.RegisterScreen
import com.dorothy.baselineapp.ui.screens.authentication.signup.SignupScreen
import com.dorothy.baselineapp.ui.screens.HomeScreen.HomeScreen
import com.dorothy.baselineapp.ui.screens.onboarding.OnboardingScreen
import com.dorothy.baselineapp.ui.screens.AddScreen.AddScreen
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = ROUTES.Onboarding.name,
        modifier = modifier
    ){
        composable(ROUTES.Onboarding.name) {
            OnboardingScreen(onFinished = {
                navController.navigate(ROUTES.Login.name) {
                    popUpTo(ROUTES.Onboarding.name) { inclusive = true }
                }
            })
        }
        composable(ROUTES.Login.name) { LoginScreen(navController = navController, modifier = Modifier) }
        composable(ROUTES.ForgotPassword.name) { ForgotPasswordScreen(navController = navController, modifier = Modifier) }
        composable(ROUTES.Register.name) { RegisterScreen(navController = navController) }
        composable(ROUTES.Signup.name) { SignupScreen(navController = navController) }
        composable(ROUTES.Home.name) { HomeScreen(navController = navController) }
        composable(ROUTES.Add.name) { 
            AddScreen(
                navController = navController,
                viewModel = viewModel()
            ) 
        }
    }
}