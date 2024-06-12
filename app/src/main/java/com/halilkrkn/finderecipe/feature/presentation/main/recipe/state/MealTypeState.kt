package com.halilkrkn.finderecipe.feature.presentation.main.recipe.state

import com.halilkrkn.finderecipe.domain.model.recipe.Recipe

data class MealTypeState(
    val mealTypesList: List<Recipe> ?= emptyList(),
    val isLoading: Boolean = false,
    val error: String ?= ""
)
