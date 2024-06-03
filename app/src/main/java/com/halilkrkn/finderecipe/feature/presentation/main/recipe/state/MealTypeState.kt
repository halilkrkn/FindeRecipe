package com.halilkrkn.finderecipe.feature.presentation.main.recipe.state

import com.halilkrkn.finderecipe.domain.model.MealTypeRecipe
import com.halilkrkn.finderecipe.domain.model.Recipe

data class MealTypeState(
    val mealTypesList: List<MealTypeRecipe> ?= emptyList(),
    val isLoading: Boolean = false,
    val error: String ?= ""
)
