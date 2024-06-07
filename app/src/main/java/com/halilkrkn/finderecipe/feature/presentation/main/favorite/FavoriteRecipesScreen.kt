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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.feature.presentation.components.AppTopBar
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.feature.presentation.components.RecipeListItem
import com.halilkrkn.finderecipe.feature.presentation.components.SearchBar
import com.halilkrkn.finderecipe.feature.presentation.main.favorite.component.FavoriteRecipeListItemScreen
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import kotlinx.coroutines.launch

@Composable
fun FavoriteRecipesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel : FavoriteRecipesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val searchQuery = viewModel.searchQuery.value
    val recipeList = state.recipeList
    val isLoading = state.isLoading

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
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
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = viewModel::onSearchRecipe,
                onClick = { viewModel.onSearchRecipe("") }

            )
            Spacer(modifier = Modifier.height(16.dp))

            if (state.recipeList.isEmpty() && !state.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LoadingProgressBar(
                        modifier = Modifier
                            .size(width = 200.dp, height = 200.dp),
                        raw = R.raw.empty_search
                    )
                    Text(
                        text = if (searchQuery.isEmpty()) "No Favorites Recipes" else "No Favorites Recipe with '$searchQuery'" ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                FavoriteRecipeListItemScreen(
                    recipeList = recipeList,
                    navController = navController,
                    deleteClick = { movies ->
                        viewModel.onDeleteFavoritesRecipe(movies)
                        scope.launch {
                            when (snackbarHostState.showSnackbar(
                                message = "Movie deleted from favorites",
                                actionLabel = "Undo",
                                withDismissAction = true,
                                duration = SnackbarDuration.Short
                            )) {
                                SnackbarResult.ActionPerformed -> {
                                    viewModel.onInsertFavoritesRecipe(movies)
                                    viewModel.onRefresh()

                                }
                                SnackbarResult.Dismissed -> {
                                    viewModel.onDeleteFavoritesRecipe(movies)
                                }
                            }
                        }
                        viewModel.onRefresh()
                    }
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
            }
        }
    }
}