package com.halilkrkn.finderecipe.domain.repository

import com.halilkrkn.finderecipe.data.remote.dto.response.recipe.AllRecipeResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe.RecipeResponse
import com.halilkrkn.finderecipe.domain.model.Recipe

interface FindeRecipeRepository {
        suspend fun getRecipes(): AllRecipeResponse

        suspend fun getMealTypeRecipes(mealType: String): AllRecipeResponse
        suspend fun getSearchRecipe(query: String): AllRecipeResponse
}
