package com.halilkrkn.finderecipe.domain.repository

import kotlinx.coroutines.flow.Flow

interface FindeRecipeOnBoardingRepository {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}