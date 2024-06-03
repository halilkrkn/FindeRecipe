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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.halilkrkn.finderecipe.domain.model.Recipe
import com.halilkrkn.finderecipe.ui.theme.Copper
import com.halilkrkn.finderecipe.ui.theme.LemonMeringue

@Composable
fun RecipeList(
    modifier: Modifier = Modifier,
    recipe: Recipe? = null,
    onItemClick: (Recipe) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .size(300.dp, 200.dp)
            .padding(start = 8.dp, end = 8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable {
//                        onItemClick(theMeal)
                }
                .background(
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            LemonMeringue,
                            Copper,
                        )
                    )
                )
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SubcomposeAsyncImage(
                model = recipe?.image,
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
                contentDescription = recipe?.title,
                modifier = Modifier
                    .weight(1f)
                    .size(312.dp, 231.dp)
            )
            recipe?.title?.let {
                Text(
                    text = it,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .height(75.dp)

                )
            }
        }
    }
}