package com.halilkrkn.finderecipe.feature.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.halilkrkn.finderecipe.feature.navigation.routes.DetailsRoutes
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs
import com.halilkrkn.finderecipe.feature.presentation.main.detail.DetailRecipeScreen
import com.halilkrkn.finderecipe.feature.presentation.main.recent_recipe.RecipeListScreen

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
            val recipeId = navBackStackEntry.arguments?.getInt("recipeId")
            navBackStackEntry.savedStateHandle.set<String>("recipeId", recipeId.toString())
            DetailRecipeScreen(navController = navController)
        }

        composable(route = DetailsRoutes.RecipeList.route) {
            RecipeListScreen(navController = navController)
        }
    }
}