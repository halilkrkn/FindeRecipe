package com.halilkrkn.finderecipe.feature.presentation.main.favorite.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar


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
//        positionalThreshold = { 150.dp.toPx() }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            DismissBackground(
                dismissState = dismissState
            )
        },
        enableDismissFromStartToEnd = false,
        content = {
            FavoriteItem(
                recipe = recipe,
                onItemClick = { recipe ->
                    onItemClick(recipe)
                }
            )
        }
    )
}


@Composable
fun FavoriteItem(
    recipe: Recipe,
    onItemClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
    onDelete: (Recipe) -> Unit = {},
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(5.dp),
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    onItemClick(recipe)
                }
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)

        ) {
            SubcomposeAsyncImage(
                model = recipe.image,
                loading = {
                    Box(
                        modifier = Modifier
                            .size(100.dp, 100.dp)
                            .background(Color.Black.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingProgressBar(
                            modifier = Modifier
                                .size(50.dp, 50.dp),
                            raw = R.raw.loading
                        )
                    }
                },
                error = {
                    LoadingProgressBar(
                        modifier = Modifier
                            .fillMaxSize()
                            .size(50.dp, 50.dp)
                            .padding(12.dp),
                        raw = R.raw.image_error
                    )
                },
                contentDescription = "RecipeFavoriteImage",
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
