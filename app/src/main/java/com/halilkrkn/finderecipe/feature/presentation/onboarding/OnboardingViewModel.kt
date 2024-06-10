package com.halilkrkn.finderecipe.feature.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.finderecipe.domain.usecase.onboarding.FindeRecipeOnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onBoardingUseCase: FindeRecipeOnBoardingUseCase,
) : ViewModel() {

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            onBoardingUseCase.saveOnBoardingState(completed)
        }
    }
}