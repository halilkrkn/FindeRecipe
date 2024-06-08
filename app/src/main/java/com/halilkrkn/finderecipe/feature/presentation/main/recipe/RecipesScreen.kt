package com.halilkrkn.finderecipe.feature.presentation.main.recipe

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.halilkrkn.finderecipe.core.util.MealTypes
import com.halilkrkn.finderecipe.feature.navigation.routes.DetailsRoutes
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.components.CategoryInfoSection
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.components.InfoSection
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.components.InputChipSection
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.components.RecentRecipesListSection
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.components.RecipeMealTypeSection
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.components.TopBar
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import kotlinx.coroutines.launch

@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecipeViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val state = viewModel.state.value
    val isLoading = state.isLoading
    val error = state.error
    val recipeList = state.recipeList

    val mealTypeRecipeList = viewModel.stateMealType.value.mealTypesList

    val mealTypes = listOf(
        MealTypes.Breakfast,
        MealTypes.Drink,
        MealTypes.Dessert,
        MealTypes.Soup,
        MealTypes.Salad,
        MealTypes.Bread,
        MealTypes.MainCourse,
        MealTypes.Appetizer,
        MealTypes.Beverage,
        MealTypes.Sauce,
        MealTypes.Marinade,
        MealTypes.FingerFood,
        MealTypes.Snack,
        MealTypes.SideDish
    )
    Scaffold(
        containerColor = FloralWhite,
        topBar = { TopBar() }
    ) { innerPadding ->

        val verticalScrollState = rememberScrollState()
        Column(
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(verticalScrollState)
                .fillMaxSize()
        ) {
            InfoSection()
            Spacer(modifier = Modifier.height(12.dp))
            CategoryInfoSection(
                title = "Recent Recipes",
                secondTitle = "See All",
                onClick = {
                    navController.navigate(DetailsRoutes.RecipeList.route)
                }
            )
            if (recipeList != null) {
                RecentRecipesListSection(
                    recipeList = recipeList,
                    isLoading = isLoading,
                    navController = navController
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            CategoryInfoSection(
                title = "Meal Types",
            )
            InputChipSection(
                onClick = { mealType ->
                    scope.launch {
                        Log.d("TAG", "InputChipSection: $mealType clicked")
                        viewModel.getMealTypeRecipes(mealType)
                    }
                },
                mealTypes = mealTypes,
            )
            Spacer(modifier = Modifier.height(12.dp))
            RecipeMealTypeSection(
                mealTypes = mealTypeRecipeList ?: emptyList(),
                navController = navController,
                isLoading = isLoading,
                error = error,
                onFavoriteClick = { recipe ->
                    viewModel.onFavoriteInsertRecipe(recipe)
                }
            )
        }
    }
}

@Preview
@Composable
private fun RecipesScreenPreview() {
    RecipesScreen(navController = rememberNavController())
}