package com.halilkrkn.finderecipe.feature.presentation.main.recipe

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

@Composable
fun RecipesScreen(
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
                text = "Recipes Screen",
            )
        }
    }
}