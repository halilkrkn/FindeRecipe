package com.halilkrkn.finderecipe.domain.repository

import com.halilkrkn.finderecipe.data.local.entity.RecipeEntity
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe.AllRecipeResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.RecipeDetailResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.similar_recipe.SimilarRecipeResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface FindeRecipeRepository {

        // Network Operations
        suspend fun getRecipes(): Response<AllRecipeResponse>
        suspend fun getMealTypeRecipes(mealType: String): Response<AllRecipeResponse>
        suspend fun getSearchRecipe(query: String): Response<AllRecipeResponse>
        suspend fun getRecipeDetail(id: Int): Response<RecipeDetailResponse>
        suspend fun getSimilarRecipes(id: Int): Response<List<SimilarRecipeResponse>>

        // Database Operations
        suspend fun insertFavoriteRecipe(recipe: RecipeEntity)
        suspend fun deleteFavoriteRecipe(recipe: RecipeEntity)
        fun getAllFavoriteRecipes(): Flow<List<RecipeEntity>>
        fun searchFavoriteRecipe(searchQuery: String): Flow<List<RecipeEntity>>
}
