package com.halilkrkn.finderecipe.feature.presentation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.halilkrkn.finderecipe.feature.navigation.graphs.BottomNavGraph
import com.halilkrkn.finderecipe.feature.navigation.routes.BottomBarRoutes
import com.halilkrkn.finderecipe.ui.theme.DarkCrulean
import com.halilkrkn.finderecipe.ui.theme.DarkMidnightBlue
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import com.halilkrkn.finderecipe.ui.theme.FloralWhite2

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {innerPadding ->
        BottomNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarRoutes.Recipes,
        BottomBarRoutes.Search,
        BottomBarRoutes.Favorites,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val isBottomBarVisible = currentDestination in screens.map { bottomBarScreen ->
        bottomBarScreen.route
    }

    if (isBottomBarVisible) {
        NavigationBar(
            containerColor = FloralWhite
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarRoutes,
    currentDestination: String?,
    navController: NavHostController,
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination == screen.route,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        alwaysShowLabel = false,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = DarkMidnightBlue,
            unselectedIconColor = DarkCrulean,
            indicatorColor = FloralWhite2
        ),
    )
}