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
    suspend fun getAllRecipes(): Flow<Resource<List<Recipe>?>> = flow {
        emit(Resource.Loading())
        val getAllRecipes = repository.getRecipes().body()?.recipeResponses?.map { recipeResponse ->
            recipeResponse.toRecipe()
        }
        val response = repository.getRecipes()

        if (response.isSuccessful) {
            emit(Resource.Success(getAllRecipes))
        }

        if (response.code() == 400 || response.code() == 401 || response.code() == 402 || response.code() == 403) {
            val message = response.errorBody()?.string()
            emit(Resource.Error(message ?: "Unable to send message. Please try again later."))
        }
    }
}
