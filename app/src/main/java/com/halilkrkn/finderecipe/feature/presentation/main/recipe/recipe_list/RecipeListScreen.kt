@file:OptIn(ExperimentalMaterial3Api::class)

package com.halilkrkn.finderecipe.feature.presentation.main.recipe.recipe_list

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.feature.navigation.routes.DetailsRoutes
import com.halilkrkn.finderecipe.feature.navigation.routes.DetailsRoutes.*
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.feature.presentation.components.RecipeListItem
import com.halilkrkn.finderecipe.feature.presentation.components.SearchBar
import com.halilkrkn.finderecipe.ui.theme.FloralWhite

@Composable
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel : RecipeListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val searchQuery = viewModel.searchQuery.value
    val recipeList = state.recipeList
    val isLoading = state.isLoading

    Scaffold(
        containerColor = FloralWhite,
        topBar = {
            TopAppBar(
                title = {
                        Text(text = "Recent Recipes")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = FloralWhite
                )
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
                onSearchQueryChange = viewModel::onSearch,
                onClick = { viewModel.onSearch("") }

            )
            Spacer(modifier = Modifier.height(16.dp))
            if (recipeList != null) {
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
            }
        }
    }
}





