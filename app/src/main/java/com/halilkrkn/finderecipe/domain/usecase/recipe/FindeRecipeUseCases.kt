package com.halilkrkn.finderecipe.domain.usecase.recipe

data class FindeRecipeUseCases(
    val getAllRecipesUseCase: GetAllRecipesUseCase,
    val getMealTypeRecipesUseCase: GetMealTypeRecipesUseCase,
    val getSearchRecipesUseCase: GetSearchRecipesUseCase,
    val getRecipeDetailUseCase: GetRecipeDetailUseCase,
    val getSimilarRecipesUseCase: GetSimilarRecipesUseCase,
    val getFindeRecipeFavoriteUseCase: GetFindeRecipeFavoriteUseCase,
)
