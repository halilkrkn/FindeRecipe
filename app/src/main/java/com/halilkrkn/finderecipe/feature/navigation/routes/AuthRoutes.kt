package com.halilkrkn.finderecipe.feature.navigation.routes

sealed class AuthRoutes(val route: String) {
    data object LogIn : AuthRoutes(route = "logIn")
    data object SignUp : AuthRoutes(route = "signUp")
    data object ForgotPassword : AuthRoutes(route = "forgotPassword")
}