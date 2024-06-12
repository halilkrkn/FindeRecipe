package com.halilkrkn.finderecipe.feature.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoadingProgressBar(
    modifier: Modifier = Modifier,
    raw: Int,
    speed : Float = 1f,
) {
    Box(
        modifier = modifier
            .size(150.dp,150.dp),
        contentAlignment = Alignment.Center,
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(raw))
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever,
            speed = speed,
            alignment = Alignment.Center,
            modifier = modifier
        )
    }
}