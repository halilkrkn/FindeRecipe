package com.halilkrkn.finderecipe.feature.presentation.main.detail.components

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
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.core.constant.Constants
import com.halilkrkn.finderecipe.core.constant.Constants.IMAGE_URL
import com.halilkrkn.finderecipe.domain.model.recipe_detail.RecipeDetail
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar

@Composable
fun DetailRecipeIngredients(recipeDetail: RecipeDetail) {
    Row(modifier = Modifier.wrapContentWidth()) {
        Text(
            text = "Ingredients",
            fontSize = 28.sp,
            fontWeight = FontWeight.W500,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
    }

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
                            color = Color.Transparent, shape = RoundedCornerShape(12.dp)
                        ), contentAlignment = Alignment.Center
                ) {
                    SubcomposeAsyncImage(
                        model = Constants.IMAGE_URL + ingredient.image,
                        loading = {
                            Box(
                                modifier = Modifier.background(Color.Black.copy(alpha = 0.1f)),
                                contentAlignment = Alignment.Center
                            ) {
                                LoadingProgressBar(
                                    modifier = Modifier.size(40.dp, 40.dp), raw = R.raw.loading
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
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.wrapContentWidth()
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
}