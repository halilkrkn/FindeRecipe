@file:OptIn(ExperimentalMaterial3Api::class)

package com.halilkrkn.finderecipe.feature.presentation.main.recipe.recipe_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.feature.presentation.components.RecipeList
import com.halilkrkn.finderecipe.feature.presentation.components.SearchBar
import com.halilkrkn.finderecipe.ui.theme.FloralWhite

@Composable
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
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
            SearchBar()
            Spacer(modifier = Modifier.height(16.dp))
            RecipeList {}
        }
    }
}





