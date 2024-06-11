package com.halilkrkn.finderecipe.core.work_manager

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.halilkrkn.finderecipe.data.local.db.FindeRecipeDatabase
import com.halilkrkn.finderecipe.data.remote.api.FindeRecipeApi
import com.halilkrkn.finderecipe.domain.usecase.recipe.FindeRecipeUseCases
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.RecipeViewModel
import javax.inject.Inject

class ApiWorkerFactory @Inject constructor(
    private val api: FindeRecipeApi,
    private val db: FindeRecipeDatabase,
): WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker {
        return ApiWorker(appContext, workerParameters, api, db)
    }
}