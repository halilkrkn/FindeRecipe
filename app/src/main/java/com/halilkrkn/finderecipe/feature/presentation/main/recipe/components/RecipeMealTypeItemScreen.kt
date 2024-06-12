package com.halilkrkn.finderecipe.feature.presentation.main.recipe.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.feature.navigation.routes.DetailsRoutes


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedAnimatable")
@Composable
fun RecipeMealTypeItemScreen(
    mealTypes: List<Recipe>,
    navController: NavController,
    onFavoriteClick: (Recipe) -> Unit,
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            columns = GridCells.Fixed(2),
        ) {

            items(mealTypes) { mealType ->
                RecipeMealTypeItem(
                    recipe = mealType,
                    modifier = Modifier.fillMaxWidth(),
                    onItemClick = {
                        navController.navigate(
                            DetailsRoutes.Detail.route + "/${mealType.id}"
                        )
                    },
                    onFavoriteClick = { favoriteRecipe ->
                        onFavoriteClick(favoriteRecipe)
                    },
                )
            }
        }
    }
}