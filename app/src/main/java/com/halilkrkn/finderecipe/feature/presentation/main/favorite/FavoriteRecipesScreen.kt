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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.feature.presentation.components.AppTopBar
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.feature.presentation.components.SearchBar
import com.halilkrkn.finderecipe.feature.presentation.main.favorite.component.FavoriteRecipeListItemScreen
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
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
    val isRefreshing = viewModel.isRefreshing.value

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = Unit) {
        viewModel.onRefresh()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = FloralWhite,
        topBar = {
            AppTopBar(
                title = "Favorite Recipes"
            )
        }
    ) { innerPadding ->

        val pullRefresh = rememberPullRefreshState(
            refreshing = isRefreshing,
            onRefresh = {
                viewModel.onRefresh()
            }
        )

        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .pullRefresh(pullRefresh),
        ) {
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = viewModel::onSearchRecipe,
                onClick = { viewModel.onSearchRecipe("") }

            )
            Spacer(modifier = Modifier.height(16.dp))

            if (recipeList.isEmpty() && !state.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LoadingProgressBar(
                        modifier = Modifier
                            .size(width = 200.dp, height = 200.dp),
                        raw = R.raw.empty_page
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
                                message = "Recipes deleted from favorites",
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

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefresh,
                backgroundColor = Color.Black,
                contentColor = FloralWhite,
                scale = true
            )
        }
    }
}