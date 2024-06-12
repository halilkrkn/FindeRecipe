package com.halilkrkn.finderecipe.feature.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.halilkrkn.finderecipe.feature.navigation.routes.SplashRoute
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs
import com.halilkrkn.finderecipe.feature.presentation.splash.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = SplashRoute.Splash.route,
        route = Graphs.SPLASH
    ) {
        composable(
            route = SplashRoute.Splash.route
        ) {
            SplashScreen(navController = navController)
        }
    }
}