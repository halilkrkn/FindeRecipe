package com.halilkrkn.finderecipe.domain.model.recipe_detail

data class Step(
    val ingredients: List<Ingredient>,
    val number: Int,
    val step: String
)