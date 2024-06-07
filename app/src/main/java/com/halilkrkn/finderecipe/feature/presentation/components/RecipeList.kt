package com.halilkrkn.finderecipe.feature.presentation.components

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
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.ui.theme.PastelBlue

@Composable
fun RecipeList(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    onItemClick: (Recipe) -> Unit = {},
    onFavoriteClick: (Recipe) -> Unit = {}
) {
    val imageChangeColor  = rememberSaveable {
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
                            PastelBlue,
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
                        imageChangeColor.value = !imageChangeColor.value
                        onFavoriteClick(recipe)
                    },
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.BottomEnd)

                ) {
                    Icon(
                        imageVector = if (imageChangeColor.value)  Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (imageChangeColor.value) PastelBlue else Color.Red
                    )
                }

            }
        }
    }
}

@Preview
@Composable
private fun RecipeListPreview() {
    RecipeList(recipe = Recipe(
        id = 1,
        title = "Recipe Title",
        image = "https://img.spoonacular.com/recipes/716429-312x231.jpg",
        imageType = "jpg",
    ))
}