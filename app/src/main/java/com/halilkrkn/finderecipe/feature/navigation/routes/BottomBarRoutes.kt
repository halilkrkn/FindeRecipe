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
    data object Home : BottomBarRoutes(
        route = "home",
        icon = Icons.Default.Cookie,
        title = "Watchlist"
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


