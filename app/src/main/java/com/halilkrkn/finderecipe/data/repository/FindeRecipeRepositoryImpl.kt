package com.halilkrkn.finderecipe.data.repository

import com.halilkrkn.finderecipe.data.local.db.FindeRecipeDatabase
import com.halilkrkn.finderecipe.data.remote.api.FindeRecipeApi
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe.AllRecipeResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.RecipeDetailResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.similar_recipe.SimilarRecipeResponse
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeRepository
import retrofit2.Response
import javax.inject.Inject

class FindeRecipeRepositoryImpl @Inject constructor(
    private val api: FindeRecipeApi,
    private val db: FindeRecipeDatabase
): FindeRecipeRepository {
    override suspend fun getRecipes(): Response<AllRecipeResponse> {
        return api.getRecipes()
    }

    override suspend fun getMealTypeRecipes(mealType: String): Response<AllRecipeResponse> {
        return api.getMealTypeRecipes(mealType = mealType)
    }

    override suspend fun getSearchRecipe(query: String): Response<AllRecipeResponse> {
       return api.getSearchRecipes(query = query)
    }

    override suspend fun getRecipeDetail(id: Int): Response<RecipeDetailResponse> {
        return api.getRecipeDetail(id = id)
    }

    override suspend fun getSimilarRecipes(id: Int): Response<List<SimilarRecipeResponse>> {
        return api.getSimilarRecipes(id = id)
    }
}