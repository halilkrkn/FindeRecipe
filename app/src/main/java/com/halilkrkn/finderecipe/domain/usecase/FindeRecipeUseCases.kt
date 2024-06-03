package com.halilkrkn.finderecipe.domain.usecase

data class FindeRecipeUseCases(
    val getAllRecipesUseCase: GetAllRecipesUseCase,
    val getMealTypeRecipesUseCase: GetMealTypeRecipesUseCase,
    val getSearchRecipesUseCase: GetSearchRecipesUseCase
)
