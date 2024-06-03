package com.halilkrkn.finderecipe.feature.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.domain.model.Recipe

@Composable
fun RecipeList(
    modifier: Modifier = Modifier,
    recipe: Recipe? = null,
    onItemClick: (Recipe) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .clickable {
//                    Log.d("TAG", "RecentRecipesItemSection: ${recipe.title} clicked")
                    if (recipe != null) {
                        onItemClick(recipe)
                    }
                }
                .fillMaxWidth()
                .size(300.dp, 300.dp),
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
                contentDescription = recipe?.title,
                modifier = Modifier
                    .size(312.dp, 231.dp)
                    .clip(RoundedCornerShape(36.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            recipe?.title?.let {
                Text(
                    text = it,
                    fontSize = 18.sp,
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