package com.halilkrkn.finderecipe.feature.presentation.main.recent_recipe.state

import com.halilkrkn.finderecipe.domain.model.recipe.Recipe

data class RecentRecipeState(
    val recipeList: List<Recipe> ?= emptyList(),
    val recipe: Recipe ?= null,
    val isLoading: Boolean = false,
    val error: String ?= "",
    val isFavorite: Boolean = false
)
