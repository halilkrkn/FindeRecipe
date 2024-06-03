package com.halilkrkn.finderecipe.feature.presentation.main.search

import com.halilkrkn.finderecipe.domain.model.Recipe

data class SearchRecipeState(
    val recipeList: List<Recipe> ?= emptyList(),
    val isLoading: Boolean = false,
    val error: String ?= ""
)
