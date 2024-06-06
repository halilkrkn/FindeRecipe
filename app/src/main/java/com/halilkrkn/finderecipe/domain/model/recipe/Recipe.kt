package com.halilkrkn.finderecipe.domain.model.recipe

import com.google.gson.annotations.SerializedName

data class Recipe(
    val id: Int,
    val image: String?,
    val imageType: String,
    val title: String
)
