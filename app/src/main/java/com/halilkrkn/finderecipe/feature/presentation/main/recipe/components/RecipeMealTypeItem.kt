package com.halilkrkn.finderecipe.feature.presentation.main.recipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.core.util.MealTypes
import com.halilkrkn.finderecipe.ui.theme.PastelBlue


@Composable
fun RecipeMealTypeItem(
    theMeal: MealTypes,
    modifier: Modifier = Modifier,
    onItemClick: (MealTypes) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)

    ){
        Card(
            modifier = modifier
                .fillMaxWidth()
                .size(200.dp, 200.dp)
                .padding(start = 4.dp, end = 4.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
               containerColor = PastelBlue
            )
        ) {
            Column(
                modifier = Modifier
                    .clickable {
                        onItemClick(theMeal)
                    }
                    .fillMaxWidth()
                    .padding(start = 8.dp)

            ) {
                SubcomposeAsyncImage(
                    model = " https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg",
                    loading = {
                        Box(
                            modifier = Modifier
                                .background(Color.Black.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
//                        LoadingProgressBar(
//                            modifier = Modifier
//                                .size(100.dp, 100.dp),
//                            raw = R.raw.image_loading
//                        )
                        }
                    },
                    error = {
//                    LoadingProgressBar(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .size(100.dp, 100.dp),
//                        raw = R.raw.image_error
//                    )
                    },
                    contentDescription = theMeal.type,
                    modifier = Modifier
                        .weight(0.75f)
                        .height(300.dp)
                )
                HorizontalDivider()
                Text(
                    text = "Quinoa and Chickpea Salad with Sun-Dried Tomatoes and Dried Cherries",
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    modifier = Modifier
                        .padding(1.dp)
                        .fillMaxWidth()
                )
            }
        }

    }
}

