package com.halilkrkn.finderecipe.domain.model.recipe_detail

data class ExtendedIngredient(
    val id: Int,
    val image: String,
    val name: String,
    val nameClean: String,
    val measures: Measures
)