package com.halilkrkn.finderecipe.feature.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.halilkrkn.finderecipe.feature.navigation.routes.OnBoardingRoute
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs
import com.halilkrkn.finderecipe.feature.presentation.onboarding.OnboardingScreen

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = OnBoardingRoute.OnBoarding.route,
        route = Graphs.ONBOARDING
    ) {
        composable(
            route = OnBoardingRoute.OnBoarding.route
        ) {
            OnboardingScreen(navController = navController)
        }
    }
}