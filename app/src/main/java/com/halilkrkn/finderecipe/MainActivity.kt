package com.halilkrkn.finderecipe

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.halilkrkn.finderecipe.feature.navigation.graphs.SetupNavGraph
import com.halilkrkn.finderecipe.ui.theme.FindeRecipeTheme
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import com.halilkrkn.finderecipe.ui.theme.FloralWhite2

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
fun setStatusBarColor(color: Color = FloralWhite2) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        LaunchedEffect(key1 = true) {
            val window = (view.context as Activity).window
            window.statusBarColor = color.toArgb()
        }
    }
}