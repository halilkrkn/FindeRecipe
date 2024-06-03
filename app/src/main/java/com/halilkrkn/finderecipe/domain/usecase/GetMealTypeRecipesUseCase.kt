package com.halilkrkn.finderecipe.domain.usecase

import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.data.mappers.toMealTypeRecipe
import com.halilkrkn.finderecipe.data.mappers.toRecipe
import com.halilkrkn.finderecipe.domain.model.MealTypeRecipe
import com.halilkrkn.finderecipe.domain.model.Recipe
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealTypeRecipesUseCase @Inject constructor(
    private val repository: FindeRecipeRepository
) {
    suspend fun getMealTypeRecipes(mealType: String): Flow<Resource<List<MealTypeRecipe>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getMealTypeRecipes(mealType)
            val recipes = response.recipeResponses.map { recipeResponse ->
                recipeResponse.toMealTypeRecipe()
            }
            emit(Resource.Success(recipes))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}
