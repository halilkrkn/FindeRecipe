package com.halilkrkn.finderecipe.feature.presentation.main.favorite.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteRecipeListItem(
    recipe: Recipe,
    onItemClick: (Recipe) -> Unit,
    deleteClick: (Recipe) -> Unit,
) {

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { swipeToDismiss ->
            if (swipeToDismiss == SwipeToDismissBoxValue.EndToStart) {
                deleteClick(recipe)
            }
            swipeToDismiss != SwipeToDismissBoxValue.StartToEnd
        },
        positionalThreshold = {
            100f
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            SwipeToDelete(
                dismissState = dismissState
            )
        },
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true,
        modifier = Modifier.fillMaxWidth()
    ) {
        FavoriteItem(
            recipe = recipe,
            onItemClick = { recipe ->
                onItemClick(recipe)
            }
        )
    }
}


