package com.halilkrkn.finderecipe

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.halilkrkn.finderecipe.feature.navigation.graphs.SetupNavGraph
import com.halilkrkn.finderecipe.feature.presentation.splash.SplashViewModel
import com.halilkrkn.finderecipe.ui.theme.FindeRecipeTheme
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import com.halilkrkn.finderecipe.ui.theme.FloralWhiteCream
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    private val splashViewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen().apply {
            this.setKeepOnScreenCondition {
                !splashViewModel.isLoading.value
            }
        }
        setContent {
            enableEdgeToEdge()
            FindeRecipeTheme {

                setStatusBarColor()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = FloralWhite
                ) {
                    navController = rememberNavController()
                    SetupNavGraph(
                        navController = navController
                    )
                }
            }
        }
    }
}


@SuppressLint("ComposableNaming")
@Composable
fun setStatusBarColor(color: Color = FloralWhiteCream) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        LaunchedEffect(key1 = true) {
            val window = (view.context as Activity).window
            window.statusBarColor = color.toArgb()
        }
    }
}