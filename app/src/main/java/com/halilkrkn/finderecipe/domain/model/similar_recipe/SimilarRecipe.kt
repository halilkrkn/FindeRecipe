package com.halilkrkn.finderecipe.domain.model.similar_recipe

data class SimilarRecipe(
    val id: Int,
    val imageType: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
)