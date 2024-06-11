package com.halilkrkn.finderecipe

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.halilkrkn.finderecipe.core.work_manager.ApiWorker
import com.halilkrkn.finderecipe.feature.navigation.graphs.SetupNavGraph
import com.halilkrkn.finderecipe.feature.presentation.splash.SplashViewModel
import com.halilkrkn.finderecipe.ui.theme.FindeRecipeTheme
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import com.halilkrkn.finderecipe.ui.theme.FloralWhiteCream
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Duration
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    private val splashViewModel: SplashViewModel by viewModels()
    lateinit var scope: CoroutineScope

    @RequiresApi(Build.VERSION_CODES.O)
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
                    val startDestination by splashViewModel.startDestination
                    SetupNavGraph(
                        navController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }

        scope = CoroutineScope(Dispatchers.IO)
        // WorkManager Kısıtlamarı
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        scope.launch(Dispatchers.IO) {
            delay(Duration.ofMinutes(1).toMillis())
            // WorkManager işini planlama
            val workRequest = PeriodicWorkRequestBuilder<ApiWorker>(
                repeatInterval = 5,
                repeatIntervalTimeUnit = TimeUnit.MINUTES
            )
                .setConstraints(constraints)
                .setInitialDelay(1, TimeUnit.MINUTES)
                .setBackoffCriteria(BackoffPolicy.LINEAR, Duration.ofMinutes(15))
                .build()

            WorkManager.getInstance(this@MainActivity).enqueueUniquePeriodicWork(
                "RecipeUpdateWork",
                ExistingPeriodicWorkPolicy.UPDATE,
                workRequest
            )
        }
//        // WorkManager işini planlama
//        val workRequest = PeriodicWorkRequestBuilder<ApiWorker>(repeatInterval = 5, repeatIntervalTimeUnit =  TimeUnit.MINUTES)
//            .setConstraints(constraints)
//            .setInitialDelay(1, TimeUnit.MINUTES)
//            .build()
//
//        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
//            "RecipeUpdateWork",
//            ExistingPeriodicWorkPolicy.UPDATE,
//            workRequest
//        )
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