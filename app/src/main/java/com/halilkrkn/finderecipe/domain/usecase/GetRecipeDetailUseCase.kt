package com.halilkrkn.finderecipe.domain.usecase

import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.data.mappers.toRecipeDetail
import com.halilkrkn.finderecipe.domain.model.recipe_detail.RecipeDetail
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(
    private val repository: FindeRecipeRepository
) {
    suspend fun getRecipeDetail(id: Int) : Flow<Resource<RecipeDetail?>> = flow {
        emit(Resource.Loading())
        val recipeDetail = repository.getRecipeDetail(id=id).body()?.toRecipeDetail()
        val response = repository.getRecipeDetail(id=id)

        if (response.isSuccessful) {
            emit(Resource.Success(recipeDetail))
        }

        if (response.code() == 400 || response.code() == 401 || response.code() == 402 || response.code() == 403) {
            val message = response.errorBody()?.string()
            emit(Resource.Error(message ?: "Unable to send message. Please try again later."))
        }
    }
}
