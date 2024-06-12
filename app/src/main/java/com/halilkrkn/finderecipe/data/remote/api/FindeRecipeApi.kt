package com.halilkrkn.finderecipe.data.remote.api


import com.halilkrkn.finderecipe.core.constant.Constants.API_KEY
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe.AllRecipeResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.RecipeDetailResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.similar_recipe.SimilarRecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FindeRecipeApi {
    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("number")
        number: Int = 30,
        @Query("apiKey")
        apiKey: String = API_KEY,
    ): Response<AllRecipeResponse>

    @GET("recipes/complexSearch")
    suspend fun getMealTypeRecipes(
        @Query("type")
        mealType: String,
        @Query("number")
        number: Int = 30,
        @Query("apiKey")
        apiKey: String = API_KEY,
    ): Response<AllRecipeResponse>

    @GET("recipes/complexSearch")
    suspend fun getSearchRecipes(
        @Query("query")
        query: String,
        @Query("number")
        number: Int = 30,
        @Query("apiKey")
        apiKey: String = API_KEY,
    ): Response<AllRecipeResponse>

    @GET("recipes/{id}/information")
    suspend fun getRecipeDetail(
        @Path("id")
        id: Int,
        @Query("apiKey")
        apiKey: String = API_KEY,
    ): Response<RecipeDetailResponse>


    @GET("recipes/{id}/similar")
    suspend fun getSimilarRecipes(
        @Path("id")
        id: Int,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<List<SimilarRecipeResponse>>
}

