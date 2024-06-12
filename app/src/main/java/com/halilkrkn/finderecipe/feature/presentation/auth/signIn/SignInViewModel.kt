package com.halilkrkn.finderecipe.feature.presentation.auth.signIn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
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
class SignInViewModel @Inject constructor(
    private val authUseCase: FindeRecipeAuthUseCases,
) : ViewModel() {

    private val _signInState = mutableStateOf<AuthState>(AuthState())
    val signInState: State<AuthState> = _signInState

    private val _googleState = mutableStateOf(GoogleAuthState())
    val googleState: State<GoogleAuthState> = _googleState

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var job: Job? = null

    suspend fun signInWithEmailAndPassword(email: String, password: String) {
        _isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            authUseCase.signInUseCase.signInWithEmailAndPassword(email, password).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _signInState.value = AuthState(isSuccess = " Sign In Success")
                    }

                    is Resource.Loading -> {
                        _signInState.value = AuthState(isLoading = true)
                    }

                    is Resource.Error -> {
                        val message = result.message ?: "Unknown error"
                        _signInState.value = AuthState(isError = message)
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }

    suspend fun signInWithGoogle(credential: AuthCredential) {
        _isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            authUseCase.googleSignInUseCase.signInWithGoogle(credential).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _googleState.value = GoogleAuthState(success = result.data)
                    }

                    is Resource.Loading -> {
                        _googleState.value = GoogleAuthState(success = result.data)
                    }

                    is Resource.Error -> {
                        val message = result.message ?: "Unknown error"
                        _googleState.value = GoogleAuthState(error = message)
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }

    fun resetSignInState() {
        _isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            _googleState.value = GoogleAuthState(
                success = null,
                loading = false,
                error = ""
            )
        }
        _isLoading.value = false
    }
}