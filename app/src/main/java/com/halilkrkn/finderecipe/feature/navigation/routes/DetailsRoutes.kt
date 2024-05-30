package com.halilkrkn.finderecipe.feature.navigation.routes

sealed class DetailsRoutes(val route: String) {
   data object Detail : DetailsRoutes("detail")
}