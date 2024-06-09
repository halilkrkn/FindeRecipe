package com.halilkrkn.finderecipe.feature.presentation.main.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.domain.usecase.recipe.FindeRecipeUseCases
import com.halilkrkn.finderecipe.feature.presentation.main.detail.state.DetailRecipeState
import com.halilkrkn.finderecipe.feature.presentation.main.detail.state.SimilarRecipeState
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

    private val _similarRecipeState = mutableStateOf(SimilarRecipeState())
    val similarRecipeState = _similarRecipeState

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val recipeId: String = (savedStateHandle.get<Int>("recipeId") ?: 0).toString()

    private var job: Job? = null

    init {
        getRecipeDetail(id = recipeId.toInt())
//        getSimilarRecipe(id = recipeId.toInt())
    }

    private fun getRecipeDetail(id: Int) {
        _isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            recipeUseCase.getRecipeDetailUseCase.getRecipeDetail(id = id).onEach { result ->
                when (result) {
                    is Resource.Success -> {

                        val recipeDetail = result.data
                        _state.value = DetailRecipeState(
                            isLoading = false,
                            recipeDetail = recipeDetail,
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

    fun similarRecipe(id: Int) {
        getSimilarRecipe(id = id)
    }

    private fun getSimilarRecipe(id: Int) {
        _isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            recipeUseCase.getSimilarRecipesUseCase.getSimilarRecipe(id = id).onEach { result ->
                when (result) {
                    is Resource.Success -> {

                        val similarRecipes = result.data
                        val similarRecipe = similarRecipes?.first()
                        _similarRecipeState.value = SimilarRecipeState(
                            isLoading = false,
                            similarRecipes = similarRecipes,
                            similarRecipe = similarRecipe,
                            error = ""
                        )
                    }

                    is Resource.Loading -> {
                        _similarRecipeState.value = SimilarRecipeState(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _similarRecipeState.value = SimilarRecipeState(
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