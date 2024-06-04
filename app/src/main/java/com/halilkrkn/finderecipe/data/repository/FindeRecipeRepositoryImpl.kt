package com.halilkrkn.finderecipe.data.repository

import com.halilkrkn.finderecipe.data.remote.api.FindeRecipeApi
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe.AllRecipeResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe.RecipeResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.RecipeDetailResponse
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeRepository
import retrofit2.Response
import javax.inject.Inject

class FindeRecipeRepositoryImpl @Inject constructor(
    private val api: FindeRecipeApi
): FindeRecipeRepository {
    override suspend fun getRecipes(): AllRecipeResponse {
        return api.getRecipes()
    }

    override suspend fun getMealTypeRecipes(mealType: String): AllRecipeResponse {
        return api.getMealTypeRecipes(mealType = mealType)
    }

    override suspend fun getSearchRecipe(query: String): AllRecipeResponse {
       return api.getSearchRecipes(query = query)
    }

    override suspend fun getRecipeDetail(id: Int): Response<RecipeDetailResponse> {
        return api.getRecipeDetail(id = id)
    }
}