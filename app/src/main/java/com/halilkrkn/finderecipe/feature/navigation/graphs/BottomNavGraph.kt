package com.halilkrkn.finderecipe.feature.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.halilkrkn.finderecipe.feature.navigation.routes.BottomBarRoutes
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs
import com.halilkrkn.finderecipe.feature.presentation.main.favorite.FavoriteRecipesScreen
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.RecipesScreen
import com.halilkrkn.finderecipe.feature.presentation.main.search.SearchRecipesScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        route = Graphs.MAIN,
        startDestination = BottomBarRoutes.Recipes.route,
        modifier = modifier
    ) {

        composable(route = BottomBarRoutes.Recipes.route) {
            RecipesScreen(navController = navController)
        }

        composable(route = BottomBarRoutes.Search.route) {
            SearchRecipesScreen(navController = navController)
        }

        composable(route = BottomBarRoutes.Favorites.route) {
            FavoriteRecipesScreen(navController = navController)
        }

        detailsNavGraph(navController = navController)
    }
}