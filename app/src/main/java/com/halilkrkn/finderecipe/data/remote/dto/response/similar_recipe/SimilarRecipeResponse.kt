package com.halilkrkn.finderecipe.data.remote.dto.response.similar_recipe


import com.google.gson.annotations.SerializedName

data class SimilarRecipeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageType")
    val imageType: String,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int,
    @SerializedName("servings")
    val servings: Int,
    @SerializedName("sourceUrl")
    val sourceUrl: String,
    @SerializedName("title")
    val title: String
)