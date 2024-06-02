package com.halilkrkn.finderecipe.data.mappers

import com.halilkrkn.finderecipe.data.remote.dto.response.recipe.RecipeResponse
import com.halilkrkn.finderecipe.domain.model.Recipe

fun RecipeResponse.toRecipe() = Recipe(
    id = id,
    image = image,
    imageType = imageType,
    title = title
)