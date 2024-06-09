package com.halilkrkn.finderecipe.domain.usecase.recipe

import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.data.local.entity.RecipeEntity
import com.halilkrkn.finderecipe.data.mappers.toRecipeResponse
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFindeRecipeFavoriteUseCase  @Inject constructor(
    private val repository: FindeRecipeRepository,
) {
    suspend fun insertFavoriteRecipe(recipe: RecipeEntity) = repository.insertFavoriteRecipe(recipe)
    suspend fun deleteFavoriteRecipe(recipe: RecipeEntity) = repository.deleteFavoriteRecipe(recipe)

    fun getAllFavoriteRecipes() : Flow<Resource<List<Recipe>>> = flow {
        emit(Resource.Loading())
        val response = repository.getAllFavoriteRecipes().first().map {recipeEntity ->
            recipeEntity.toRecipeResponse()
        }
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "An unexpected error occurred"))
    }

    fun searchFavoriteRecipe(searchQuery: String): Flow<Resource<List<Recipe>>> = flow {
        emit(Resource.Loading())
        val response = repository.searchFavoriteRecipe(searchQuery).first().map { recipeEntity ->
            recipeEntity.toRecipeResponse()
        }
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "An unexpected error occurred"))
    }
}
