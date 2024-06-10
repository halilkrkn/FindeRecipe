package com.halilkrkn.finderecipe.feature.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs


@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        route = Graphs.ROOT,
        startDestination = startDestination
    ) {
        splashNavGraph(navController = navController)
        onboardingNavGraph(navController = navController)
        authNavGraph(navController = navController)
        mainNavGraph(navController = navController)
    }
}