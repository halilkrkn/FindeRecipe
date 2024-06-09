package com.halilkrkn.finderecipe.domain.usecase.recipe

import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.data.mappers.toRecipe
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchRecipesUseCase@Inject constructor(
    private val repository: FindeRecipeRepository
) {
    suspend fun getSearchRecipes(query: String) : Flow<Resource<List<Recipe>?>> = flow {
        emit(Resource.Loading())
        val recipes = repository.getSearchRecipe(query = query).body()?.recipeResponses?.map { recipeResponse ->
            recipeResponse.toRecipe()
        }
        val response = repository.getSearchRecipe(query = query)

        if (response.isSuccessful) {
            emit(Resource.Success(recipes))
        }

        if (response.code() == 400 || response.code() == 401 || response.code() == 402 || response.code() == 403) {
            val message = response.errorBody()?.string()
            emit(Resource.Error(message ?: "Unable to send message. Please try again later."))
        }
    }
}
