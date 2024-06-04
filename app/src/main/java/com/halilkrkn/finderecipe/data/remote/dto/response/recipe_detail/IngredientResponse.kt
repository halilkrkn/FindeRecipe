package com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail


import com.google.gson.annotations.SerializedName

data class IngredientResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("localizedName")
    val localizedName: String,
    @SerializedName("name")
    val name: String
)