package com.halilkrkn.finderecipe.feature.presentation.auth.forgotPassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.domain.usecase.auth.FindeRecipeAuthUseCases
import com.halilkrkn.finderecipe.feature.presentation.auth.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPassswordViewModel  @Inject constructor(
    private val authUseCase: FindeRecipeAuthUseCases,
) : ViewModel() {

    private val _forgotPasswordState = mutableStateOf<AuthState>(AuthState())
    val forgotPasswordState: State<AuthState> = _forgotPasswordState

    private val _sendForgotEmail = mutableStateOf(false)
    val sendForgotEmail: State<Boolean> = _sendForgotEmail

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var job: Job? = null

    suspend fun forgotPasswordWithEmail(email: String) {
        _isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            authUseCase.forgotPasswordUseCase.forgotPasswordWithEmail(email).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _forgotPasswordState.value = AuthState(isSuccess = " Forgot Password Success")
                    }

                    is Resource.Loading -> {
                        _forgotPasswordState.value = AuthState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _forgotPasswordState.value = AuthState(isError = result.message ?: "Forgot Password Error")
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
        _sendForgotEmail.value = true
    }
}