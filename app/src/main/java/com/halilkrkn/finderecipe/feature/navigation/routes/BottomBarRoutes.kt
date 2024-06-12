package com.halilkrkn.finderecipe.feature.navigation.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarRoutes(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    data object Recipes : BottomBarRoutes(
        route = "recipes",
        icon = Icons.Default.Cookie,
        title = "Recipes"
    )
    data object Search : BottomBarRoutes(
        route = "search",
        icon = Icons.Filled.Search,
        title = "Search"
    )
    data object Favorites : BottomBarRoutes(
        route = "favorites",
        icon = Icons.Filled.Favorite,
        title = "Favorites"
    )
}


