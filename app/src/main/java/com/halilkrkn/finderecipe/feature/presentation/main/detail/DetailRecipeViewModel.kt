package com.halilkrkn.finderecipe.feature.presentation.main.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.domain.usecase.FindeRecipeUseCases
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
class DetailRecipeViewModel @Inject constructor(
    private val recipeUseCase: FindeRecipeUseCases,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(DetailRecipeState())
    val state = _state

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val ids = savedStateHandle.get<String>("recipeId") ?: "0"

    private var job: Job? = null

//    fun getRecipeDetail(id: Int) {
//        getRecipeDetail(id = id)
//    }
    init {
    Log.d("TAG", "ids: $ids")
        getRecipeDetail(ids.toInt())
    }

    private fun getRecipeDetail(id: Int) {
        _isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            recipeUseCase.getRecipeDetailUseCase.getRecipeDetail(id = id).onEach { result ->
                when (result) {
                    is Resource.Success -> {

                        val campaign = result.data
                        _state.value = DetailRecipeState(
                            isLoading = false,
                            recipeDetail = null,
                            error = ""
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = DetailRecipeState(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _state.value = DetailRecipeState(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }
}