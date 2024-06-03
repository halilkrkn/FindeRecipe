package com.halilkrkn.finderecipe.domain.model

import com.google.gson.annotations.SerializedName

data class MealTypeRecipe(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String
)
