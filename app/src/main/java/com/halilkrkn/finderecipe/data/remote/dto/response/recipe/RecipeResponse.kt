package com.halilkrkn.finderecipe.data.remote.dto.response.recipe


import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("imageType")
    val imageType: String,
    @SerializedName("title")
    val title: String
)