package com.halilkrkn.finderecipe.feature.presentation.main.recipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.feature.navigation.routes.DetailsRoutes
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar

@Composable
fun RecentRecipesListSection(
    modifier: Modifier = Modifier,
    recipeList: List<Recipe>,
    isLoading: Boolean,
    navController: NavController,
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .size(120.dp, 120.dp)
                .padding(24.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            LoadingProgressBar(
                raw = R.raw.loading
            )
        }
    }

    if (recipeList.isEmpty() && !isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoadingProgressBar(
                modifier = Modifier
                    .size(width = 200.dp, height = 200.dp),
                raw = R.raw.image_error
            )
            Text(
                text = if (recipeList.isEmpty()) "No answer found for Recent Recipes!" else "No answer found for Recent Recipes!",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(recipeList) { recipe ->
            RecentRecipesItemSection(
                recipe = recipe,
                onClick = {
                    navController.navigate(DetailsRoutes.Detail.route.plus("/${recipe.id}"))
                }
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}