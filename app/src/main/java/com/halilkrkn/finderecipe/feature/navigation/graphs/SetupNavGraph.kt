package com.halilkrkn.finderecipe.feature.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs


@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = Graphs.ROOT,
        startDestination = Graphs.SPLASH
    ) {
        splashNavGraph(navController = navController)
        authNavGraph(navController = navController)
        mainNavGraph(navController = navController)
    }
}