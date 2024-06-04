package com.halilkrkn.finderecipe.feature.presentation.main.recipe.state

import com.halilkrkn.finderecipe.domain.model.recipe.Recipe

data class RecipeState(
    val recipeList: List<Recipe> ?= emptyList(),
    val isLoading: Boolean = false,
    val error: String ?= ""
)
