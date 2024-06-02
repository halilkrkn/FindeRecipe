package com.halilkrkn.finderecipe.data.remote.api


import com.halilkrkn.finderecipe.core.constant.Constants.API_KEY
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe.AllRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FindeRecipeApi {
    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("number")
        number: Int = 30,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): AllRecipeResponse
}