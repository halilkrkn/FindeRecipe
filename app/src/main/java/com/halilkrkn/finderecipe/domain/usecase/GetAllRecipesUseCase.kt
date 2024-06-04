package com.halilkrkn.finderecipe.domain.usecase

import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.data.mappers.toRecipe
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllRecipesUseCase @Inject constructor(
    private val repository: FindeRecipeRepository
) {
    suspend fun getAllRecipes(): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getRecipes()
            val recipes = response.recipeResponses.map {recipeResponse ->
                recipeResponse.toRecipe()
            }
            emit(Resource.Success(recipes))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}
