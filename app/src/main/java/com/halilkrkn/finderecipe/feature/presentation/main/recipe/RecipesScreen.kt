@file:OptIn(ExperimentalMaterialApi::class)

package com.halilkrkn.finderecipe.feature.presentation.main.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.halilkrkn.finderecipe.ui.theme.DarkMidnightBlue
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
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
    val isRefresh by viewModel.isRefreshing

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

    LaunchedEffect(key1 = true) {
        viewModel.getAllRecentRecipes()
    }
    Scaffold(
        containerColor = FloralWhite,
        topBar = {
            TopBar(
                navController = navController,
            )
        }
    ) { innerPadding ->

        val pullRefresh = rememberPullRefreshState(
            refreshing = isRefresh,
            onRefresh = {
                viewModel.onRefresh()
            }
        )

        val verticalScrollState = rememberScrollState()
        Column(
            modifier = modifier
                .padding(innerPadding)
                .pullRefresh(pullRefresh)
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
            RecentRecipesListSection(
                recipeList = recipeList,
                isLoading = isLoading,
                navController = navController
            )
            Spacer(modifier = Modifier.height(12.dp))
            CategoryInfoSection(
                title = "Meal Types",
            )
            InputChipSection(
                onClick = { mealType ->
                    scope.launch {
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

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PullRefreshIndicator(
                refreshing = isRefresh,
                state = pullRefresh,
                backgroundColor = Color.Black,
                contentColor = FloralWhite,
                scale = true
            )
        }
    }
}

@Preview
@Composable
private fun RecipesScreenPreview() {
    RecipesScreen(navController = rememberNavController())
}