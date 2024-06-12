package com.halilkrkn.finderecipe.feature.presentation.main.recipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.ui.theme.FloralWhiteCream
import com.halilkrkn.finderecipe.ui.theme.PastelBlue


@Composable
fun RecipeMealTypeItem(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onItemClick: (Recipe) -> Unit,
    onFavoriteClick: (Recipe) -> Unit,
) {
    val isFavoriteRecipe  = remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .size(300.dp, 225.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onItemClick(recipe)
                }
                .background(
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            Color.Transparent,
                            FloralWhiteCream,
                        )
                    )
                )
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .size(312.dp, 231.dp)
            ) {
                // Image
                SubcomposeAsyncImage(
                    model = recipe.image,
                    loading = {
                        Box(
                            modifier = Modifier
                                .background(Color.Black.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            LoadingProgressBar(
                                modifier = Modifier
                                    .size(100.dp, 100.dp),
                                raw = R.raw.loading
                            )
                        }
                    },
                    error = {
                        LoadingProgressBar(
                            modifier = Modifier
                                .fillMaxSize()
                                .size(100.dp, 100.dp),
                            raw = R.raw.image_error
                        )
                    },
                    contentDescription = recipe.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(312.dp, 231.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)

            ){
                Text(
                    text = recipe.title,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )

                IconButton(
                    onClick = {
                        isFavoriteRecipe.value = !isFavoriteRecipe.value
                        onFavoriteClick(recipe)
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.BottomEnd)

                ) {
                    Icon(
                        imageVector = if (isFavoriteRecipe.value)  Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavoriteRecipe.value) Color.Red else Color.Gray
                    )
                }

            }
        }
    }
}


