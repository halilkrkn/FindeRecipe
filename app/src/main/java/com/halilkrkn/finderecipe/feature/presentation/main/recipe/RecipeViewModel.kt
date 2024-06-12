package com.halilkrkn.finderecipe.feature.presentation.main.recipe

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.data.mappers.toRecipeEntity
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.domain.usecase.recipe.FindeRecipeUseCases
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.state.MealTypeState
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.state.RecipeFavoriteState
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.state.RecipeState
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
class RecipeViewModel @Inject constructor(
    private val recipeUseCases: FindeRecipeUseCases,
) : ViewModel() {
    private val _state = mutableStateOf(RecipeState())
    val state: State<RecipeState> = _state

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isRefreshing = mutableStateOf(false)
    val isRefreshing: State<Boolean> = _isRefreshing


    private var job: Job? = null

    init {
        getAllRecipes()
        getMealTypeRecipe("breakfast")
    }

    fun onRefresh() {
        _isRefreshing.value = true
        _isLoading.value = true
        getAllRecipes()
        getMealTypeRecipe("breakfast")
        _isRefreshing.value = false
        _isLoading.value = false
    }

    fun getAllRecentRecipes() {
        getAllRecipes()
    }

    private fun getAllRecipes() {
        _isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            recipeUseCases.getAllRecipesUseCase.getAllRecipes().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        val recipeList = result.data
                        _state.value = RecipeState(
                            isLoading = false,
                            recipeList = recipeList ?: emptyList(),
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

    fun getMealTypeRecipes(mealType: String) {
        getMealTypeRecipe(mealType)
    }


    private val _stateMealType = mutableStateOf(MealTypeState())
    val stateMealType: State<MealTypeState> = _stateMealType
    private fun getMealTypeRecipe(mealType: String) {
        _isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            recipeUseCases.getMealTypeRecipesUseCase.getMealTypeRecipes(mealType = mealType)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {

                            val mealTypeList = result.data
                            _stateMealType.value = MealTypeState(
                                isLoading = false,
                                mealTypesList = mealTypeList ?: emptyList(),
                                error = ""
                            )
                        }

                        is Resource.Loading -> {
                            _stateMealType.value = MealTypeState(
                                isLoading = true,
                                error = ""
                            )
                        }

                        is Resource.Error -> {
                            _stateMealType.value = MealTypeState(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }

    private val _stateFavorite = mutableStateOf(RecipeFavoriteState())
    val stateFavorite: State<RecipeFavoriteState> = _stateFavorite
    fun onFavoriteInsertRecipe(recipe: Recipe) {
        insertFavoriteRecipe(recipe)
        _stateFavorite.value = RecipeFavoriteState(
            isLoading = false,
            recipe = recipe,
        )
    }

    private fun insertFavoriteRecipe(recipe: Recipe) =
        viewModelScope.launch(Dispatchers.IO) {
            recipeUseCases.getFindeRecipeFavoriteUseCase.insertFavoriteRecipe(recipe.toRecipeEntity())
        }
}