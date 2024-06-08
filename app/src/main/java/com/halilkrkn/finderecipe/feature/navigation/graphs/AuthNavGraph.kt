package com.halilkrkn.finderecipe.feature.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.halilkrkn.finderecipe.feature.navigation.routes.AuthRoutes
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs
import com.halilkrkn.finderecipe.feature.presentation.auth.forgot_password.ForgotPasswordScreen
import com.halilkrkn.finderecipe.feature.presentation.auth.login.LogInScreen
import com.halilkrkn.finderecipe.feature.presentation.auth.register.SignUpScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = AuthRoutes.LogIn.route,
        route = Graphs.AUTHENTICATION
    ) {

        composable(
            route = AuthRoutes.LogIn.route
        ) {
            LogInScreen(navController = navController)
        }

        composable(
            route = AuthRoutes.SignUp.route
        ) {
            SignUpScreen(navController = navController)
        }

        composable(
            route = AuthRoutes.ForgotPassword.route
        ) {
            ForgotPasswordScreen(navController = navController)
        }
    }
}