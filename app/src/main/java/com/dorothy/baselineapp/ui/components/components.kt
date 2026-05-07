package com.dorothy.baselineapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


val `pagepadding`: Dp = 16.dp

@Composable
fun LottieAnimationWidget(lottiePath:Int,size: Dp) {
    val comet = 0
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottiePath))
    val progress by animateLottieCompositionAsState(composition,
        iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier.size(size)
    )
}