@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.halilkrkn.finderecipe.feature.presentation.main.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.core.constant.Constants.IMAGE_URL
import com.halilkrkn.finderecipe.core.util.DetailRecipeEvents
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.feature.presentation.main.detail.components.CookingInstructionsSteps
import com.halilkrkn.finderecipe.feature.presentation.main.detail.components.InstructionsNowBottomSheet
import com.halilkrkn.finderecipe.ui.theme.Coral
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import com.halilkrkn.finderecipe.ui.theme.PastelBlue

@Composable
fun DetailRecipeScreen(
    navController: NavController,
    viewModel: DetailRecipeViewModel = hiltViewModel(),
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val showBottomSheet = remember { mutableStateOf(false) }

    val state = viewModel.state.value
    val recipeDetail = state.recipeDetail
    val loading = state.isLoading
    val error = state.error

    Scaffold(
        containerColor = FloralWhite,
        topBar = {
            TopAppBar(
                title = {
                    if (recipeDetail != null) {
                        Text(
                            text = recipeDetail.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    }
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
        if (recipeDetail != null) {
            val verticalScrollState = rememberScrollState()
            Column(
                Modifier
                    .verticalScroll(verticalScrollState)
                    .padding(innerPadding)
                    .wrapContentSize()
            ) {
                Box {
                    // Image
                    SubcomposeAsyncImage(
                        model = recipeDetail.image,
                        loading = {
                            Box(
                                modifier = Modifier
                                    .background(Color.Black.copy(alpha = 0.1f)),
                                contentAlignment = Alignment.Center
                            ) {
                                LoadingProgressBar(
                                    modifier = Modifier.size(100.dp, 100.dp),
                                    raw = R.raw.loading
                                )
                            }
                        },
                        error = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon( // Use an icon to indicate error
                                    imageVector = Icons.Default.Error,
                                    contentDescription = "Failed to load image"
                                )
                            }
                        },
                        contentDescription = "recipe.title",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp)
                            .size(312.dp, 275.dp)
                            .clip(RoundedCornerShape(36.dp)),
                        contentScale = ContentScale.Crop
                    )
                }


                val detailEvents = DetailRecipeEvents(recipeDetail = recipeDetail)
                val events = listOf(
                    detailEvents.events
                ).first()

                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(events) { detailRecipeEvent ->
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(
                                    color = Color.Transparent
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .size(32.dp),
                                painter = painterResource(id = detailRecipeEvent.icon),
                                contentDescription = "icon"
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = detailRecipeEvent.text,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = detailRecipeEvent.result,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500,
                                color = Color.Black
                            )
                        }
                    }
                }

                Row(modifier = Modifier.wrapContentWidth()) {
                    Text(
                        text = "Ingredients",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.W500,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .requiredHeight(825.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        items(recipeDetail.extendedIngredients) { ingredient ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .width(94.dp)
                                    .clickable { },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                Box(
                                    modifier = Modifier
                                        .requiredSize(60.dp)
                                        .background(
                                            color = Color.Transparent,
                                            shape = RoundedCornerShape(12.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    SubcomposeAsyncImage(
                                        model = IMAGE_URL + ingredient.image,
                                        loading = {
                                            Box(
                                                modifier = Modifier
                                                    .background(Color.Black.copy(alpha = 0.1f)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                LoadingProgressBar(
                                                    modifier = Modifier.size(40.dp, 40.dp),
                                                    raw = R.raw.loading
                                                )
                                            }
                                        },
                                        error = {
                                            Box(
                                                modifier = Modifier.fillMaxSize(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon( // Use an icon to indicate error
                                                    imageVector = Icons.Default.Error,
                                                    contentDescription = "Failed to load image"
                                                )
                                            }
                                        },
                                        contentDescription = "recipe.title",
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        contentScale = ContentScale.Crop
                                    )
                                }

                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .wrapContentWidth()
                                ) {
                                    Text(
                                        text = ingredient.name,
                                        modifier = Modifier.wrapContentWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = ingredient.measures.metric.amount.toString() + " g",
                                        modifier = Modifier.wrapContentWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                        }
                    }
                    Row(modifier = Modifier.wrapContentWidth()) {
                        Text(
                            text = "Instructions",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.Black,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Column {
                        Text(
                            text = recipeDetail.title,
                            modifier = Modifier.padding(16.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .wrapContentWidth()
                                .clickable {
                                    showBottomSheet.value = true
                                }
                                .padding(16.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(Coral)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {

                                    Text(
                                        text = "View All Steps",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W500,
                                        color = FloralWhite
                                    )
                                }
                            }
                        }
                    }
                    InstructionsNowBottomSheet(
                        sheetState = sheetState,
                        showBottomSheet = showBottomSheet,
                        recipeDetail = recipeDetail
                    ) { recipeDetail ->
                        CookingInstructionsSteps(recipeDetail = recipeDetail)
                    }
                    Column {
                        Text(
                            text = "Similar Recipes",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.Black,
                            modifier = Modifier.padding(16.dp)
                        )
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(8) {
                                Column(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(
                                            color = PastelBlue,
                                            shape = RoundedCornerShape(12.dp)
                                        ),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    SubcomposeAsyncImage(
                                        model = "https://img.spoonacular.com/ingredients_250x250/cooked-chicken-breast.png",
                                        loading = {
                                            Box(
                                                modifier = Modifier
                                                    .background(Color.Black.copy(alpha = 0.1f)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                LoadingProgressBar(
                                                    modifier = Modifier.size(40.dp, 40.dp),
                                                    raw = R.raw.loading
                                                )
                                            }
                                        },
                                        error = {
                                            Box(
                                                modifier = Modifier.fillMaxSize(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon( // Use an icon to indicate error
                                                    imageVector = Icons.Default.Error,
                                                    contentDescription = "Failed to load image"
                                                )
                                            }
                                        },
                                        contentDescription = "recipe.title",
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        contentScale = ContentScale.Fit
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Chicken Breast",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.W500,
                                        color = Color.Black
                                    )
                                }
                            }

                        }

                    }

                }
                if (loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingProgressBar(
                            modifier = Modifier,
                            raw = R.raw.loading
                        )
                    }
                }
                if (error != null) {
                    if (error.isNotBlank()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = error,
                                color = Color.Red,
                                fontSize = 16.sp
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
