package com.halilkrkn.finderecipe.feature.presentation.main.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.feature.presentation.components.AppTopBar
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.feature.presentation.components.RecipeListItem
import com.halilkrkn.finderecipe.feature.presentation.components.SearchBar
import com.halilkrkn.finderecipe.ui.theme.FloralWhite

@Composable
fun FavoriteRecipesScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(
        containerColor = FloralWhite,
        topBar = {
            AppTopBar(
                title = "Favorite Recipes"
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
/*            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = viewModel::onSearch,
                onClick = { viewModel.onSearch("") }

            )
            Spacer(modifier = Modifier.height(16.dp))

            if (state.recipeList?.isEmpty() == true && !state.isLoading) {
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
                        text = if (searchQuery.isEmpty()) "Please make a call." ?: "No answer found for meal types" else "No answer found for '$searchQuery'" ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }

            if (recipeList != null) {
                RecipeListItem(
                    recipeList = recipeList,
                    isLoading = isLoading,
                    navController = navController,
                )
            }

            if (state.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LoadingProgressBar(raw = R.raw.loading)
                }
            }*/
        }
    }
}