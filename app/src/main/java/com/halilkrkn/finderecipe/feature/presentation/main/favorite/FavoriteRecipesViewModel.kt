package com.halilkrkn.finderecipe.feature.presentation.main.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.data.mappers.toRecipeEntity
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.domain.usecase.FindeRecipeUseCases
import com.halilkrkn.finderecipe.feature.presentation.main.favorite.state.FavoriteRecipeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteRecipesViewModel @Inject constructor(
    private val recipeUseCases: FindeRecipeUseCases,
) : ViewModel() {
    private val _state = mutableStateOf<FavoriteRecipeState>(FavoriteRecipeState())
    val state: State<FavoriteRecipeState> = _state

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _searchQuery = mutableStateOf<String>("")
    val searchQuery: State<String> = _searchQuery

    private var movieJob: Job? = null

    init {
        getFavoriteFindeRecipes()
    }

    fun onRefresh() {
        getFavoriteFindeRecipes()
    }

    private fun getFavoriteFindeRecipes() {
        _isLoading.value = true
        movieJob?.cancel()
        movieJob = viewModelScope.launch(Dispatchers.IO) {
            recipeUseCases.getFindeRecipeFavoriteUseCase.getAllFavoriteRecipes().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = FavoriteRecipeState(
                            isLoading = false,
                            recipeList = result.data ?: emptyList()
                        )
                    }

                    is Resource.Error -> {
                        _state.value = FavoriteRecipeState(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occured"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = FavoriteRecipeState(
                            isLoading = true
                        )
                    }
                    else -> {
                        _state.value = FavoriteRecipeState(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occured"
                        )}
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }

    fun onDeleteFavoritesRecipe(recipe: Recipe) {
        deleteFavoriteRecipe(recipe)
    }

    private fun deleteFavoriteRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeUseCases.getFindeRecipeFavoriteUseCase.insertFavoriteRecipe(recipe.toRecipeEntity())
        }
    }

    fun onInsertFavoritesRecipe(recipe: Recipe) {
        insertFavoriteRecipe(recipe)
    }

    private fun insertFavoriteRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeUseCases.getFindeRecipeFavoriteUseCase.deleteFavoriteRecipe(recipe.toRecipeEntity())
        }
    }

    private var searchJob: Job? = null
    fun onSearchRecipe(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            recipeUseCases.getFindeRecipeFavoriteUseCase.searchFavoriteRecipe(query)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = FavoriteRecipeState(
                                isLoading = false,
                                recipeList = result.data ?: emptyList()
                            )
                        }

                        is Resource.Error -> {
                            _state.value = FavoriteRecipeState(
                                isLoading = false,
                                error = result.message ?: "An unexpected error occured"
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = FavoriteRecipeState(
                                isLoading = true
                            )
                        }

                        else -> {
                            _state.value = FavoriteRecipeState(
                                isLoading = false,
                                error = result.message ?: "An unexpected error occured"
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
}