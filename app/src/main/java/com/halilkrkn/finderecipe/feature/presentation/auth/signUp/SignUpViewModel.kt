package com.halilkrkn.finderecipe.feature.presentation.auth.signUp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.domain.usecase.auth.FindeRecipeAuthUseCases
import com.halilkrkn.finderecipe.feature.presentation.auth.state.AuthState
import com.halilkrkn.finderecipe.feature.presentation.auth.state.GoogleAuthState
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

class SignUpViewModel @Inject constructor(
    private val authUseCase: FindeRecipeAuthUseCases,
) : ViewModel() {

    private val _signUpState = mutableStateOf<AuthState>(AuthState())
    val signUpState: State<AuthState> = _signUpState

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var job: Job? = null

    suspend fun signUpWithEmailAndPassword(email: String, password: String) {
        _isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            authUseCase.signUpUseCase.signUpWithEmailAndPassword(email, password).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _signUpState.value = AuthState(isSuccess = " Sign Up Success")
                    }

                    is Resource.Loading -> {
                        _signUpState.value = AuthState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _signUpState.value = AuthState(isError = result.message ?: "Sign Up Error")
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }
}