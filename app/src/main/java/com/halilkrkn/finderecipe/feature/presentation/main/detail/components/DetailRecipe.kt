package com.halilkrkn.finderecipe.feature.presentation.main.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.domain.model.recipe_detail.RecipeDetail
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar

@Composable
fun DetailRecipeImage(
    modifier: Modifier = Modifier,
    recipeDetail: RecipeDetail,
) {
    Box {
        // Image
        SubcomposeAsyncImage(
            model = recipeDetail.image,
            loading = {
                Box(
                    modifier = Modifier.background(Color.Black.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingProgressBar(
                        modifier = Modifier.size(100.dp, 100.dp), raw = R.raw.loading
                    )
                }
            },
            error = {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Icon(
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
}







