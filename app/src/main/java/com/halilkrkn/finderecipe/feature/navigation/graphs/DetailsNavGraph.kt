package com.halilkrkn.finderecipe.feature.navigation.graphs

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.halilkrkn.finderecipe.feature.navigation.routes.BottomBarRoutes
import com.halilkrkn.finderecipe.feature.navigation.routes.DetailsRoutes
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs
import com.halilkrkn.finderecipe.feature.presentation.main.detail.DetailRecipeScreen
import com.halilkrkn.finderecipe.feature.presentation.main.favorite.FavoriteRecipesScreen
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.recipe_list.RecipeListScreen

fun NavGraphBuilder.detailsNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = DetailsRoutes.Detail.route,
        route = Graphs.DETAILS
    ) {
        composable(
            route = DetailsRoutes.Detail.route.plus("/{recipeId}"), arguments = listOf(navArgument("recipeId") {
                defaultValue = -1
                type = NavType.IntType },
            )) {navBackStackEntry ->
            val recipeId = navBackStackEntry.arguments?.getInt("movieId")
            navBackStackEntry.savedStateHandle.set("recipeId", recipeId)
            DetailRecipeScreen(navController = navController)
        }

        composable(route = DetailsRoutes.RecipeList.route) {
            RecipeListScreen(navController = navController)
        }
    }
}