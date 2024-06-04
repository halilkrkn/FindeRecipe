package com.halilkrkn.finderecipe.feature.presentation.main.detail

import com.halilkrkn.finderecipe.domain.model.recipe_detail.RecipeDetail

data class DetailRecipeState(
    val recipeDetail: RecipeDetail ?= null,
    val isLoading: Boolean = false,
    val error: String ?= ""
)