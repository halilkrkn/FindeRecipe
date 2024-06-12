package com.halilkrkn.finderecipe

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
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
        // WorkManager Kısıtlamaları
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // İlk çalıştırma için OneTimeWorkRequest
        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<ApiWorker>()
            .setConstraints(constraints)
            .setInitialDelay(1, TimeUnit.MINUTES)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this).enqueueUniqueWork(
            "OneTimeRecipeUpdateWork",
            ExistingWorkPolicy.REPLACE,
            oneTimeWorkRequest
        )

        // PeriodicWorkRequest oluşturulması
        val periodicWorkRequest = PeriodicWorkRequestBuilder<ApiWorker>(
            15, TimeUnit.MINUTES
        ).build()

        // OneTimeWorkRequest tamamlandıktan sonra PeriodicWorkRequest başlatma
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this) { workInfo ->
                if (workInfo != null && workInfo.state.isFinished) {
                    WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                        "PeriodicRecipeUpdateWork",
                        ExistingPeriodicWorkPolicy.UPDATE,
                        periodicWorkRequest
                    )
                }
            }

        // WorkManager iş durumunu kontrol etmek için log eklemek
        WorkManager.getInstance(this).getWorkInfosForUniqueWorkLiveData("PeriodicRecipeUpdateWork")
            .observe(this) { workInfos ->
                if (workInfos.isNotEmpty()) {
                    val workInfo = workInfos[0]
                    Log.d("WorkManager", "WorkInfo state: ${workInfo.state}")
                    if (workInfo.state == WorkInfo.State.ENQUEUED) {
                        Log.d("WorkManager", "Work enqueued and waiting")
                    } else if (workInfo.state == WorkInfo.State.RUNNING) {
                        Log.d("WorkManager", "Work is running")
                    } else if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                        Log.d("WorkManager", "Work succeeded")
                    } else if (workInfo.state == WorkInfo.State.FAILED) {
                        Log.d("WorkManager", "Work failed")
                    } else if (workInfo.state == WorkInfo.State.BLOCKED) {
                        Log.d("WorkManager", "Work is blocked")
                    } else if (workInfo.state == WorkInfo.State.CANCELLED) {
                        Log.d("WorkManager", "Work is cancelled")
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