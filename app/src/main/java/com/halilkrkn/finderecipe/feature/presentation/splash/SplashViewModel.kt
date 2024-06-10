package com.halilkrkn.finderecipe.feature.presentation.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.finderecipe.domain.usecase.onboarding.FindeRecipeOnBoardingUseCase
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs.SPLASH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val onBoardingUseCase: FindeRecipeOnBoardingUseCase
): ViewModel(){

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _startDestination: MutableState<String> = mutableStateOf(SPLASH)
    val startDestination: State<String> = _startDestination


    init {
        readOnBoardingState()

        viewModelScope.launch {
            _isLoading.value = true
        }
    }

    private fun readOnBoardingState() {
        viewModelScope.launch(Dispatchers.Main) {
            onBoardingUseCase.readOnBoardingState().collect {completed ->
                if (completed) {
                    _startDestination.value = Graphs.AUTHENTICATION
                } else {
                    _startDestination.value = Graphs.ONBOARDING
                }
            }
            _isLoading.value = false
        }
    }
}