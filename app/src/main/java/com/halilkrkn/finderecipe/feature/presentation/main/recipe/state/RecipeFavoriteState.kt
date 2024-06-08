package com.halilkrkn.finderecipe.feature.presentation.main.recipe.state

import com.halilkrkn.finderecipe.domain.model.recipe.Recipe

data class RecipeFavoriteState(
    val recipe: Recipe? = null,
    val isLoading: Boolean = false,
    val error: String ?= "",
)
