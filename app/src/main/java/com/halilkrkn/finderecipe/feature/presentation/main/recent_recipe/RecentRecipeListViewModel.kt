package com.halilkrkn.finderecipe.feature.presentation.main.recent_recipe

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.data.mappers.toRecipeEntity
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.domain.usecase.FindeRecipeUseCases
import com.halilkrkn.finderecipe.feature.presentation.main.recent_recipe.state.RecentRecipeState
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
class RecentRecipeListViewModel @Inject constructor(
    private val recipeUseCases: FindeRecipeUseCases,
) : ViewModel() {
    private val _state = mutableStateOf(RecentRecipeState())
    val state: State<RecentRecipeState> = _state

    private val _searchQuery = mutableStateOf<String>("")
    val searchQuery: State<String> = _searchQuery

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var job: Job? = null

    init {
        getSearchRecipe(query = "")
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
                            _state.value = RecentRecipeState(
                                isLoading = false,
                                recipeList = recipeList,
                                error = ""
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = RecentRecipeState(
                                isLoading = true,
                                error = ""
                            )
                        }

                        is Resource.Error -> {
                            _state.value = RecentRecipeState(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }

    fun onFavoriteRecipe(recipe: Recipe) {
        insertFavoriteRecipe(recipe)
        _state.value = RecentRecipeState(
            isLoading = false,
            recipe = recipe,
            isFavorite = true
        )
    }
private fun insertFavoriteRecipe(recipe: Recipe) =
    viewModelScope.launch(Dispatchers.IO) {
        recipeUseCases.getFindeRecipeFavoriteUseCase.insertFavoriteRecipe(recipe.toRecipeEntity())
    }
}