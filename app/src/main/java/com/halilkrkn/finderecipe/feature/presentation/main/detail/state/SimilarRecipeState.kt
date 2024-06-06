package com.halilkrkn.finderecipe.feature.presentation.main.detail.state

import com.halilkrkn.finderecipe.domain.model.similar_recipe.SimilarRecipe

data class SimilarRecipeState(
    val similarRecipes: List<SimilarRecipe> ?= emptyList(),
    val similarRecipe: SimilarRecipe ?= null,
    val isLoading: Boolean = false,
    val error: String ?= ""
)