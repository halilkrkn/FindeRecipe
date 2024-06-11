package com.halilkrkn.finderecipe.feature.presentation.main.notification.state

import com.halilkrkn.finderecipe.domain.model.recipe.Recipe

data class NotificationRecipeState (
    val isLoading: Boolean = false,
    val recipeList: List<Recipe> = emptyList(),
    val error: String = "",
)