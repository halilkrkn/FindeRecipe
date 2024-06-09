package com.halilkrkn.finderecipe.feature.presentation.main.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.data.mappers.toRecipeEntity
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.domain.usecase.recipe.FindeRecipeUseCases
import com.halilkrkn.finderecipe.feature.presentation.main.search.state.SearchFavoriteRecipeState
import com.halilkrkn.finderecipe.feature.presentation.main.search.state.SearchRecipeState
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
class SearchRecipesViewModel  @Inject constructor(
    private val recipeUseCases: FindeRecipeUseCases,
) : ViewModel() {
    private val _state = mutableStateOf(SearchRecipeState())
    val state: State<SearchRecipeState> = _state

    private val _searchQuery = mutableStateOf<String>("")
    val searchQuery: State<String> = _searchQuery

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var job: Job? = null
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
                            _state.value = SearchRecipeState(
                                isLoading = false,
                                recipeList = recipeList,
                                error = ""
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = SearchRecipeState(
                                isLoading = true,
                                error = ""
                            )
                        }

                        is Resource.Error -> {
                            _state.value = SearchRecipeState(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }

    private val _stateFavorite = mutableStateOf(SearchFavoriteRecipeState())
    val stateFavorite: State<SearchFavoriteRecipeState> = _stateFavorite
    fun onFavoriteRecipe(recipe: Recipe) {
        insertFavoriteRecipe(recipe)
        _stateFavorite.value = SearchFavoriteRecipeState(
            isLoading = false,
            recipe = recipe,
        )
    }
    private fun insertFavoriteRecipe(recipe: Recipe) =
        viewModelScope.launch(Dispatchers.IO) {
            recipeUseCases.getFindeRecipeFavoriteUseCase.insertFavoriteRecipe(recipe.toRecipeEntity())
        }
}