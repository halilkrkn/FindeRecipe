package com.halilkrkn.finderecipe.feature.presentation.main.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.data.local.entity.NotificationEntity
import com.halilkrkn.finderecipe.feature.presentation.components.AppTopBar
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.feature.presentation.components.SearchBar
import com.halilkrkn.finderecipe.feature.presentation.main.favorite.component.FavoriteRecipeListItemScreen
import com.halilkrkn.finderecipe.feature.presentation.main.notification.components.NotificationRecipeListItemScreen
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import kotlinx.coroutines.launch

@Composable
fun NotificationScreen(
    navController: NavController,
    viewModel: NotificationViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value
    val recipeList = state.recipeList
    val isLoading = state.isLoading

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        viewModel.onRefresh()
    }

    Scaffold(
        containerColor = FloralWhite,
        topBar = {
            AppTopBar(
                title = "Notification Recipes",
                onRefresh = {
                    viewModel.onRefresh()
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
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
                        text = if (recipeList.isEmpty()) "No Notification Recipes" else "No Notification Recipes",
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
                NotificationRecipeListItemScreen(
                    recipeList = recipeList,
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