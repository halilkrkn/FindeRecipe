package com.halilkrkn.finderecipe.feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.feature.navigation.routes.DetailsRoutes

@Composable
fun RecipeListItem(
    modifier: Modifier = Modifier,
    recipeList: List<Recipe>,
    isLoading: Boolean = false,
    navController: NavController,
    onFavoriteClick: (Recipe) -> Unit = {}
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .size(120.dp, 120.dp)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            LoadingProgressBar(
                raw = R.raw.loading
            )
        }
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 16.dp
    ) {
        items(recipeList) { recipe ->
            RecipeList(
                recipe = recipe,
                onItemClick = { recipeItem ->
                    navController.navigate(DetailsRoutes.Detail.route.plus("/${recipeItem.id}"))
                },
                onFavoriteClick = onFavoriteClick
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}