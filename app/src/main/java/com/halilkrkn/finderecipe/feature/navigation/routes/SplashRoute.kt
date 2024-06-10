package com.halilkrkn.finderecipe.feature.navigation.routes

sealed class SplashRoute(val route: String) {
    data object Splash : SplashRoute("splash")
}
