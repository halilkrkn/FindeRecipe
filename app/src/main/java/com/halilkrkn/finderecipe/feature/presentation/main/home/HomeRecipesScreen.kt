package com.halilkrkn.finderecipe.feature.presentation.main.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.feature.navigation.routes.DetailsRoutes
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs

@Composable
fun HomeRecipesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(onClick = {
            navController.navigate(route = DetailsRoutes.Detail.route.plus("/1"))
        }) {
            Text(
                text = "Home Recipes",
            )
        }
    }
}