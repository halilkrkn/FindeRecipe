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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.core.util.MealTypes
import com.halilkrkn.finderecipe.domain.model.MealTypeRecipe
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.ui.theme.FloralWhite2
import com.halilkrkn.finderecipe.ui.theme.LightYellow


@Composable
fun RecipeMealTypeItem(
    theMeal: MealTypeRecipe,
    modifier: Modifier = Modifier,
    onItemClick: (MealTypes) -> Unit,
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .size(300.dp, 200.dp)
            .padding(start = 8.dp, end = 8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = FloralWhite2
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable {
//                        onItemClick(theMeal)
                }
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SubcomposeAsyncImage(
                model = theMeal.image,
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
                contentDescription = theMeal.title,
                modifier = Modifier
                    .weight(1f)
                    .size(312.dp, 231.dp)
            )
            Text(
                text = theMeal.title,
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

