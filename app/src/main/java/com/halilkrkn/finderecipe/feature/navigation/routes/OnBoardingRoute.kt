package com.halilkrkn.finderecipe.feature.navigation.routes

sealed class OnBoardingRoute (val route: String) {
    data object OnBoarding : OnBoardingRoute("onboarding")
}