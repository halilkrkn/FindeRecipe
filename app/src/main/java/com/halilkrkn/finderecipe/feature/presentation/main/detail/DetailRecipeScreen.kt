@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.halilkrkn.finderecipe.feature.presentation.main.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.feature.presentation.main.detail.components.CookingInstructionsSteps
import com.halilkrkn.finderecipe.feature.presentation.main.detail.components.DetailRecipeEvent
import com.halilkrkn.finderecipe.feature.presentation.main.detail.components.DetailRecipeImage
import com.halilkrkn.finderecipe.feature.presentation.main.detail.components.DetailRecipeIngredients
import com.halilkrkn.finderecipe.feature.presentation.main.detail.components.DetailRecipeInstructions
import com.halilkrkn.finderecipe.feature.presentation.main.detail.components.RecipeBottomSheet
import com.halilkrkn.finderecipe.feature.presentation.main.detail.components.SimilarMenuSheet
import com.halilkrkn.finderecipe.feature.presentation.main.detail.components.SimilarRecipes
import com.halilkrkn.finderecipe.ui.theme.FloralWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRecipeScreen(
    navController: NavController,
    viewModel: DetailRecipeViewModel = hiltViewModel(),
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val showBottomSheet = remember { mutableStateOf(false) }
    val showSimilarBottomSheet = remember { mutableStateOf(false) }
    val similarRecipeSourceUrl: MutableState<String> = remember { mutableStateOf("") }
    val selectedUrl = remember { mutableStateOf("") }

    val state = viewModel.state.value
    val recipeDetail = state.recipeDetail
    val similarRecipeState = viewModel.similarRecipeState.value
    val similarRecipes = similarRecipeState.similarRecipes
    val similarRecipe = similarRecipeState.similarRecipe
    val loading = state.isLoading
    val error = state.error


    if (recipeDetail != null) {
        LaunchedEffect(key1 = recipeDetail.id) {
            viewModel.similarRecipe(recipeDetail.id)
        }
    }

    Scaffold(containerColor = FloralWhite, topBar = {
        TopAppBar(title = {
            if (recipeDetail != null) {
                Text(
                    text = recipeDetail.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, contentDescription = "Back"
                )
            }
        }, colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = FloralWhite
        )
        )
    }) { innerPadding ->
        if (recipeDetail != null) {
            val verticalScrollState = rememberScrollState()
            Column(
                Modifier
                    .verticalScroll(verticalScrollState)
                    .padding(innerPadding)
                    .wrapContentSize()
            ) {

                // Recipe Image
                DetailRecipeImage(
                    recipeDetail = recipeDetail
                )
                // Event Ingredients
                DetailRecipeEvent(recipeDetail = recipeDetail)

                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .requiredHeight(1500.dp)
                ) {
                    // Ingredients
                    DetailRecipeIngredients(recipeDetail = recipeDetail)
                    // Instructions
                    DetailRecipeInstructions(
                        recipeDetail = recipeDetail, showBottomSheet = showBottomSheet
                    )
                    // Instructions Now Bottom Sheet
                    RecipeBottomSheet(
                        sheetState = sheetState,
                        showBottomSheet = showBottomSheet,
                    ) {
                        CookingInstructionsSteps(recipeDetail = recipeDetail)
                    }
                    // Similar Recipes
                    if (similarRecipes != null) {
                        SimilarRecipes(
                            similarRecipes = similarRecipes,
                            showBottomSheet = showSimilarBottomSheet,
                            similarRecipeSourceUrl = similarRecipeSourceUrl
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    // Similar Menu WebView
                    RecipeBottomSheet(
                        sheetState = sheetState,
                        showBottomSheet = showSimilarBottomSheet,
                    ) {
                        if (similarRecipe != null) {
                            SimilarMenuSheet(
                                similarRecipeSourceUrl = similarRecipeSourceUrl,
                                isLoading = loading,
                            )
                        }
                    }


                }

                if (loading) {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        LoadingProgressBar(
                            modifier = Modifier, raw = R.raw.loading
                        )
                    }
                }
                if (error != null) {
                    if (error.isNotBlank()) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = error, color = Color.Red, fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DetailRecipeScreenPreview() {
    DetailRecipeScreen(navController = rememberNavController())
}
