package com.halilkrkn.finderecipe.domain.repository

import com.halilkrkn.finderecipe.data.remote.dto.response.recipe.AllRecipeResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.RecipeDetailResponse
import retrofit2.Response

interface FindeRecipeRepository {
        suspend fun getRecipes(): AllRecipeResponse

        suspend fun getMealTypeRecipes(mealType: String): AllRecipeResponse
        suspend fun getSearchRecipe(query: String): AllRecipeResponse

        suspend fun getRecipeDetail(id: Int): Response<RecipeDetailResponse>
}
