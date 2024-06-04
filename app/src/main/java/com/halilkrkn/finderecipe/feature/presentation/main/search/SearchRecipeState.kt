package com.halilkrkn.finderecipe.feature.presentation.main.search

import com.halilkrkn.finderecipe.domain.model.recipe.Recipe

data class SearchRecipeState(
    val recipeList: List<Recipe> ?= emptyList(),
    val isLoading: Boolean = false,
    val error: String ?= ""
)
