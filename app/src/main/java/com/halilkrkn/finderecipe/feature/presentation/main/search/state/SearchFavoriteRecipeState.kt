package com.halilkrkn.finderecipe.feature.presentation.main.search.state

import com.halilkrkn.finderecipe.domain.model.recipe.Recipe

data class SearchFavoriteRecipeState(
    val recipe: Recipe? = null,
    val isLoading: Boolean = false,
    val error: String ?= "",
)
