package com.halilkrkn.finderecipe.feature.presentation.main.detail.components

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.core.constant.Constants.SIMILAR_IMAGE_URL
import com.halilkrkn.finderecipe.domain.model.similar_recipe.SimilarRecipe
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.ui.theme.PastelBlue

@Composable
fun SimilarRecipes(
    similarRecipes: List<SimilarRecipe>,
    showBottomSheet: MutableState<Boolean> = remember { mutableStateOf(false) },
    similarRecipeSourceUrl: MutableState<String> = remember { mutableStateOf("") },
) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(8.dp)

    ) {
        Text(
            text = "Similar Recipes",
            fontSize = 28.sp,
            fontWeight = FontWeight.W500,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                similarRecipes,
                key = { similarRecipe -> similarRecipe.id }
            ) { similarRecipe ->
                Card(
                    modifier = Modifier
                        .clickable {
                            showBottomSheet.value = true
                            similarRecipeSourceUrl.value = similarRecipe.sourceUrl
                        }
                        .fillMaxWidth()
                        .size(300.dp, 315.dp)
                        .padding(start = 8.dp, end = 8.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(6.dp)
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SubcomposeAsyncImage(
                            model = SIMILAR_IMAGE_URL + similarRecipe.id + "-556x370." + similarRecipe.imageType,
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
                            contentDescription = "similar_image",
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Fit
                        )

                        Text(
                            text = similarRecipe.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp)
                                .padding(horizontal = 20.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(24.dp),
                                    painter = painterResource(id = R.drawable.ready_in_minutes),
                                    contentDescription = "ready_in_minutes"
                                )
                                Text(
                                    text = similarRecipe.readyInMinutes.toString() + " min",
                                    color = Color.DarkGray,
                                    fontSize = 12.sp,
                                )
                                VerticalDivider(
                                    color = PastelBlue,
                                    modifier = Modifier
                                        .height(24.dp)
                                )
                                Image(
                                    modifier = Modifier
                                        .size(24.dp),
                                    painter = painterResource(id = R.drawable.servings),
                                    contentDescription = "servings"
                                )
                                Text(
                                    text = similarRecipe.servings.toString() + " servings",
                                    color = Color.DarkGray,
                                    fontSize = 12.sp,
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}