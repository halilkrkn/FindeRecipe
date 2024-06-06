package com.halilkrkn.finderecipe.feature.presentation.main.recipe.recipe_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.domain.usecase.FindeRecipeUseCases
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.state.RecipeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeUseCases: FindeRecipeUseCases,
) : ViewModel() {
    private val _state = mutableStateOf(RecipeState())
    val state: State<RecipeState> = _state

    private val _searchQuery = mutableStateOf<String>("")
    val searchQuery: State<String> = _searchQuery

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var job: Job? = null

    init {
        getSearchRecipe(query = "chicken")
    }

    fun onSearch(query: String) {
        getSearchRecipe(query)
    }
    private fun getSearchRecipe(query: String) {
        _isLoading.value = true
        _searchQuery.value = query
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            delay(500L)
            recipeUseCases.getSearchRecipesUseCase.getSearchRecipes(query = query)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {

                            val recipeList = result.data
                            _state.value = RecipeState(
                                isLoading = false,
                                recipeList = recipeList,
                                error = ""
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = RecipeState(
                                isLoading = true,
                                error = ""
                            )
                        }

                        is Resource.Error -> {
                            _state.value = RecipeState(
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