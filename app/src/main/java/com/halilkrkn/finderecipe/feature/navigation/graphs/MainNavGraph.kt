package com.halilkrkn.finderecipe.feature.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.halilkrkn.finderecipe.feature.navigation.routes.MainRoutes
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs
import com.halilkrkn.finderecipe.feature.presentation.main.MainScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
) {
    navigation(
        route = Graphs.MAIN,
        startDestination = MainRoutes.Main.route,
    ) {
        composable(
            route = MainRoutes.Main.route
        ) {
            MainScreen()
        }
    }
}