package com.halilkrkn.finderecipe.domain.usecase

import android.util.Log
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.data.mappers.toSimilarRecipe
import com.halilkrkn.finderecipe.domain.model.similar_recipe.SimilarRecipe
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSimilarRecipesUseCase @Inject constructor(
    private val repository: FindeRecipeRepository,
) {
    suspend fun getSimilarRecipe(id: Int): Flow<Resource<List<SimilarRecipe>?>> = flow {
        emit(Resource.Loading())
        val similarRecipes = repository.getSimilarRecipes(id = id).body()?.map { similarRecipeResponse ->
            similarRecipeResponse.toSimilarRecipe()
        }
        val response = repository.getSimilarRecipes(id = id)

        if (response.isSuccessful) {
            emit(Resource.Success(similarRecipes))
        }

        if (response.code() == 400 || response.code() == 401 || response.code() == 402 || response.code() == 403) {
            val message = response.errorBody()?.string()
            emit(Resource.Error(message ?: "Unable to send message. Please try again later."))
        }
    }

}
