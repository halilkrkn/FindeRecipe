package com.halilkrkn.finderecipe.data.mappers

import com.halilkrkn.finderecipe.data.remote.dto.response.recipe.RecipeResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.AnalyzedInstructionResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.ExtendedIngredientResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.IngredientResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.MeasuresResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.MetricResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.RecipeDetailResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.StepResponse
import com.halilkrkn.finderecipe.domain.model.recipe.MealTypeRecipe
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.domain.model.recipe_detail.AnalyzedInstruction
import com.halilkrkn.finderecipe.domain.model.recipe_detail.ExtendedIngredient
import com.halilkrkn.finderecipe.domain.model.recipe_detail.Ingredient
import com.halilkrkn.finderecipe.domain.model.recipe_detail.Measures
import com.halilkrkn.finderecipe.domain.model.recipe_detail.Metric
import com.halilkrkn.finderecipe.domain.model.recipe_detail.RecipeDetail
import com.halilkrkn.finderecipe.domain.model.recipe_detail.Step

fun RecipeResponse.toRecipe() = Recipe(
    id = id,
    image = image,
    imageType = imageType,
    title = title
)

fun RecipeResponse.toMealTypeRecipe() = MealTypeRecipe(
    id = id,
    image = image,
    imageType = imageType,
    title = title
)

fun RecipeDetailResponse.toRecipeDetail() = RecipeDetail(
    id = id,
    aggregateLikes = aggregateLikes,
    analyzedInstructions = analyzedInstructions.map { analyzedInstructionsResponse ->
        analyzedInstructionsResponse.toAnalyzedInstruction()
    },
    cookingMinutes = cookingMinutes,
    creditsText = creditsText,
    extendedIngredients = extendedIngredients.map { extendedIngredientResponse ->
        extendedIngredientResponse.toExtendedIngredient()
    },
    healthScore = healthScore,
    image = image,
    imageType = imageType,
    instructions = instructions,
    preparationMinutes = preparationMinutes,
    pricePerServing = pricePerServing,
    readyInMinutes = readyInMinutes,
    servings = servings,
    sourceName = sourceName,
    sourceUrl = sourceUrl,
    spoonacularScore = spoonacularScore,
    spoonacularSourceUrl = sourceUrl,
    summary = summary,
    title = title,
    veryPopular = veryPopular
)

fun AnalyzedInstructionResponse.toAnalyzedInstruction() = AnalyzedInstruction(
    steps = steps.map { stepResponse ->
        stepResponse.toStep()
    }
)

fun StepResponse.toStep() = Step(
    number = number,
    step = step,
    ingredients = ingredients.map { ingredientResponse ->
        ingredientResponse.toIngredient()
    },
)

fun IngredientResponse.toIngredient() = Ingredient(
    id = id,
    image = image,
    localizedName = localizedName,
    name = name
)

fun ExtendedIngredientResponse.toExtendedIngredient() = ExtendedIngredient(
    id = id,
    image = image,
    name = name,
    nameClean = nameClean,
    measures = measures.toMeasures()
)

fun MeasuresResponse.toMeasures() = Measures(
    metric = metric.toMetric()
)

fun MetricResponse.toMetric() = Metric(
    amount = amount,
    unitLong = unitLong,
    unitShort = unitShort
)

fun MealTypeRecipe.toRecipe() = Recipe(
    id = id,
    image = image,
    imageType = imageType,
    title = title
)

