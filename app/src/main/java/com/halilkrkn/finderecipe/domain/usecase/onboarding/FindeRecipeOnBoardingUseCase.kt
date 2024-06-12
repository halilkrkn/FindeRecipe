package com.halilkrkn.finderecipe.domain.usecase.onboarding

import com.halilkrkn.finderecipe.domain.repository.FindeRecipeOnBoardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindeRecipeOnBoardingUseCase @Inject constructor(
    private val findeRecipeOnBoardingRepository: FindeRecipeOnBoardingRepository
) {
    suspend fun saveOnBoardingState(completed: Boolean) {
        findeRecipeOnBoardingRepository.saveOnBoardingState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return findeRecipeOnBoardingRepository.readOnBoardingState()
    }
}