package com.halilkrkn.finderecipe.feature.navigation.routes

sealed class MainRoutes(val route: String) {
    data object Main: MainRoutes("main")
}


