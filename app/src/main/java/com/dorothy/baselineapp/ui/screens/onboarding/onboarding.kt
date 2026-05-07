package com.dorothy.baselineapp.ui.screens.onboarding

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dorothy.baselineapp.ui.navigation.ROUTES
import com.dorothy.baselineapp.ui.theme.primaryColor
import com.dorothy.baselineapp.ui.theme.secondaryColor


@Composable
fun OnboardingScreen(navController: NavHostController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = " Baseline ",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = secondaryColor
            )
        )



        LottieAnimationWidget()


        Text(
            text = "safety is the cure",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedButton(onClick = { navController.navigate(ROUTES.Login.name)},

            modifier = Modifier.padding(horizontal = 24.dp),
            border =  ButtonDefaults.outlinedButtonBorder(enabled = false),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFFFFFFFF),
                containerColor = primaryColor,
            ),

            ) {
            Text(text ="login")
        }
    }
}

@Composable
fun LottieAnimationWidget() {
    val composition by rememberLottieComposition(spec = RawRes(resId = R.raw.baseline))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        composition = composition,
        modifier = Modifier.size(350.dp)
    )
}

fun rememberLottieComposition(spec: RawRes) {
    TODO("Not yet implemented")
}

@Composable
fun LottieAnimation(composition: Any, modifier: Modifier) {
    TODO("Not yet implemented")
}

fun animateLottieCompositionAsState(composition: Any, iterations: Any) {}
