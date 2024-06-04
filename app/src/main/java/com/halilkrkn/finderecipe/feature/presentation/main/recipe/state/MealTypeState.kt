package com.halilkrkn.finderecipe.feature.presentation.main.recipe.state

import com.halilkrkn.finderecipe.domain.model.recipe.MealTypeRecipe

data class MealTypeState(
    val mealTypesList: List<MealTypeRecipe> ?= emptyList(),
    val isLoading: Boolean = false,
    val error: String ?= ""
)
