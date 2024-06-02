package com.halilkrkn.finderecipe.data.remote.dto.response.recipe


import com.google.gson.annotations.SerializedName

data class AllRecipeResponse(
    @SerializedName("number")
    val number: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val recipeResponses: List<RecipeResponse>,
    @SerializedName("totalResults")
    val totalResults: Int
)