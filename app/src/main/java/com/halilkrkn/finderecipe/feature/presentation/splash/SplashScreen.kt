package com.halilkrkn.finderecipe.feature.presentation.splash

import android.os.Build
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs.AUTHENTICATION
import com.halilkrkn.finderecipe.ui.theme.FloralWhiteCream
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 5000
        ), label = "The Movies App Splash Screen Animation"
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(6000)
        navController.popBackStack()
        navController.navigate(AUTHENTICATION)
    }

    SplashScreenSetup(alpha = alphaAnim.value)
}

@Composable
fun SplashScreenSetup(alpha: Float) {
    val context = LocalContext.current
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.finde_recipe)
    )

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()


    Box(
        modifier = Modifier
            .background(color = FloralWhiteCream)
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            contentScale = ContentScale.Fit,
            painter = rememberAsyncImagePainter
                (
                model = R.raw.finde_recipe,
                imageLoader = imageLoader,
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}
