package com.halilkrkn.finderecipe.core.util

import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.domain.model.recipe_detail.RecipeDetail

class DetailRecipeEvents(
    private val recipeDetail: RecipeDetail
) {
    val events = mutableListOf(
        DetailRecipeEventModel(
            icon = R.drawable.popular,
            text = "Popular",
            result = if (recipeDetail.veryPopular) "Yes" else "No"
        ),
        DetailRecipeEventModel(
            icon = R.drawable.ready_in_minutes,
            text = "Minutes",
            result = recipeDetail.readyInMinutes.toString()
        ),
        DetailRecipeEventModel(
            icon = R.drawable.servings,
            text = "Servings",
            result = recipeDetail.servings.toString()
        ),
        DetailRecipeEventModel(
            icon = R.drawable.appreciation_likes,
            text = "Likes",
            result = recipeDetail.aggregateLikes.toString()
        ),
        DetailRecipeEventModel(
            icon = R.drawable.healt_score,
            text = "Health Score",
            result = recipeDetail.healthScore.toString()
        )
    )
}

data class DetailRecipeEventModel(
    val icon: Int,
    val text: String,
    val result: String,
)