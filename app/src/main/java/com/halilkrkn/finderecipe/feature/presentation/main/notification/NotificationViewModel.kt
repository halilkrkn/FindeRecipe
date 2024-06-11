package com.halilkrkn.finderecipe.feature.presentation.main.notification

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.domain.usecase.notification.NotificationUseCase
import com.halilkrkn.finderecipe.feature.presentation.main.notification.state.NotificationRecipeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationUseCase: NotificationUseCase,
    firebaseUser: FirebaseAuth?,
) : ViewModel() {

    private val _state = mutableStateOf<NotificationRecipeState>(NotificationRecipeState())
    val state: State<NotificationRecipeState> = _state

    val notificationCount: Int = _state.value.recipeList.size

        private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var job: Job? = null

    private val userId = firebaseUser?.currentUser?.uid.toString()

    init {
        getAllNotificationRecipes(userId = userId)
    }

    fun onRefresh() {
        getAllNotificationRecipes(userId = userId)
    }
    private fun getAllNotificationRecipes(userId: String) {
        _isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            notificationUseCase.getAllNotificationRecipes(userId = userId)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = NotificationRecipeState(
                                isLoading = false,
                                recipeList = result.data ?: emptyList()
                            )
                        }

                        is Resource.Error -> {
                            _state.value = NotificationRecipeState(
                                isLoading = false,
                                error = result.message ?: "An unexpected error occured"
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = NotificationRecipeState(
                                isLoading = true
                            )
                        }

                        else -> {
                            _state.value = NotificationRecipeState(
                                isLoading = false,
                                error = result.message ?: "An unexpected error occured"
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }
}

