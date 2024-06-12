@file:OptIn(ExperimentalMaterial3Api::class)

package com.halilkrkn.finderecipe.feature.presentation.main.recent_recipe

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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.feature.presentation.components.AppTopBar
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.feature.presentation.components.RecipeListItem
import com.halilkrkn.finderecipe.feature.presentation.components.SearchBar
import com.halilkrkn.finderecipe.ui.theme.DarkMidnightBlue
import com.halilkrkn.finderecipe.ui.theme.FloralWhite

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecentRecipeListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val searchQuery = viewModel.searchQuery.value
    val recipeList = state.recipeList
    val isLoading = state.isLoading
    val isRefresh = viewModel.isRefreshing.value

    Scaffold(
        containerColor = FloralWhite,
        topBar = {
            AppTopBar(
                title = "Recent Recipes",
                onClick = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        val pullRefresh = rememberPullRefreshState(
            refreshing = isRefresh,
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
                onSearchQueryChange = viewModel::onSearch,
                onClick = { viewModel.onSearch("") }

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
                        raw = R.raw.image_error
                    )
                    Text(
                        text = if (searchQuery.isEmpty()) "Please make a call."
                            ?: "No answer found for meal types" else "No answer found for '$searchQuery'",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }

            RecipeListItem(
                recipeList = recipeList,
                isLoading = isLoading,
                navController = navController,
                onFavoriteClick = { recipe ->
                    viewModel.onFavoriteRecipe(recipe)
                },
            )
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
                refreshing = isRefresh,
                state = pullRefresh,
                backgroundColor = Color.Black,
                contentColor = FloralWhite,
                scale = true
            )
        }
    }
}





