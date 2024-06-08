package com.halilkrkn.finderecipe.feature.presentation.main.favorite.state

import com.halilkrkn.finderecipe.domain.model.recipe.Recipe

data class FavoriteRecipeState (
    val isLoading: Boolean = false,
    val recipeList: List<Recipe> = emptyList(),
    val error: String = "",
)