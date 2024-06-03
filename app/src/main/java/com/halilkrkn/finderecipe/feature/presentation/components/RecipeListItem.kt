package com.halilkrkn.finderecipe.feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.domain.model.Recipe

@Composable
fun RecipeListItem(
    modifier: Modifier = Modifier,
    recipeList: List<Recipe>,
    isLoading: Boolean = false,
    onItemClicked: (Recipe) -> Unit
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
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(recipeList) { recipe ->
            RecipeList(
                recipe = recipe,
                onItemClick = { recipeItem ->
                    onItemClicked(recipeItem)
                }
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}