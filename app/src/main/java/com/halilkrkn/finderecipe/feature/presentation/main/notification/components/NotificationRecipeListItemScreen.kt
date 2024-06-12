package com.halilkrkn.finderecipe.feature.presentation.main.notification.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.feature.navigation.routes.DetailsRoutes
import com.halilkrkn.finderecipe.feature.presentation.main.favorite.component.FavoriteRecipeListItem

@Composable
fun NotificationRecipeListItemScreen(
    recipeList: List<Recipe>,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                recipeList,
                key = { item ->
                    item.id
                }
            ) { recipe ->
                NotificationRecipeListItem(
                    recipe = recipe,
                    onItemClick = { recipeNavigate ->
                        navController.navigate(
                            DetailsRoutes.Detail.route.plus("/${recipeNavigate.id}")
                        )
                    },
                )
            }
        }
    }
}
