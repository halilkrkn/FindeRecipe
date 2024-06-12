package com.halilkrkn.finderecipe

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.halilkrkn.finderecipe.core.work_manager.ApiWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class FindeRecipeApplication: Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: ApiWorkerFactory
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()
    }
}

