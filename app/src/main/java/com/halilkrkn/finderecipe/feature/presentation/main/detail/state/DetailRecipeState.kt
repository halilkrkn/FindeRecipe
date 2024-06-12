package com.halilkrkn.finderecipe.feature.presentation.main.detail.state

import com.halilkrkn.finderecipe.domain.model.recipe_detail.RecipeDetail
import com.halilkrkn.finderecipe.domain.model.similar_recipe.SimilarRecipe

data class DetailRecipeState(
    val recipeDetail: RecipeDetail ?= null,
    val isLoading: Boolean = false,
    val error: String ?= ""
)