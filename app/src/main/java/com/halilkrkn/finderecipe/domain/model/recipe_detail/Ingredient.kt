package com.halilkrkn.finderecipe.domain.model.recipe_detail

data class Ingredient(
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String
)