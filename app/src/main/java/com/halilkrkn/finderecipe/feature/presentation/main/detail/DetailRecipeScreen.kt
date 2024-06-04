@file:OptIn(ExperimentalMaterial3Api::class)

package com.halilkrkn.finderecipe.feature.presentation.main.detail

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.feature.presentation.main.detail.components.CookingInstructionsSteps
import com.halilkrkn.finderecipe.feature.presentation.main.detail.components.InstructionsNowBottomSheet
import com.halilkrkn.finderecipe.ui.theme.Coral
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import com.halilkrkn.finderecipe.ui.theme.FloralWhite2
import com.halilkrkn.finderecipe.ui.theme.PastelBlue

@Composable
fun DetailRecipeScreen(
    navController: NavController,
    carId: Int? = null,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet = remember { mutableStateOf(false) }

    Scaffold(
        containerColor = FloralWhite,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Recipes Title")
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
                    model = "https://img.spoonacular.com/recipes/715415-556x370.jpg",
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
                        .size(312.dp, 300.dp)
                        .clip(RoundedCornerShape(36.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            val events = listOf(
                DetailRecipeEvent.Popular,
                DetailRecipeEvent.ReadyInMinutes,
                DetailRecipeEvent.Servings,
                DetailRecipeEvent.Likes,
                DetailRecipeEvent.HealthScore
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(events) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .background(
                                color = Color.Transparent
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        it.icon?.let {
                            Image(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .size(32.dp),
                                painter = painterResource(id = it),
                                contentDescription = "icon"
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = it.text,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "6.4",
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
                    .requiredHeight(725.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp),
                ) {
                    items(8) {
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
                                    .size(60.dp)
                                    .background(
                                        color = FloralWhite2,
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                contentAlignment = Alignment.Center
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
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(36.dp)),
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
                                    text = "Chicken Breast",
                                    modifier = Modifier.wrapContentWidth(),
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "200g",
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
                        text = "Step 1: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
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
                    showBottomSheet = showBottomSheet
                ) {
                    CookingInstructionsSteps()
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

        }
    }
}

    sealed class DetailRecipeEvent(
        @DrawableRes
        val icon: Int? = null,
        val text: String,
    ) {
        data object Popular : DetailRecipeEvent(icon = R.drawable.popular, text = "Popular")
        data object ReadyInMinutes :
            DetailRecipeEvent(icon = R.drawable.ready_in_minutes, text = "Minutes")

        data object Servings : DetailRecipeEvent(icon = R.drawable.servings, text = "Servings")
        data object Likes : DetailRecipeEvent(icon = R.drawable.appreciation_likes, text = "Likes")
        data object HealthScore :
            DetailRecipeEvent(icon = R.drawable.healt_score, text = "Health Score")
    }

    @Preview
    @Composable
    private fun DetailRecipeScreenPreview() {
        DetailRecipeScreen(navController = rememberNavController())
    }
