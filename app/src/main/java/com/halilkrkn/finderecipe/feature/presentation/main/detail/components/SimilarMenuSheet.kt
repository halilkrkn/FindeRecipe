package com.halilkrkn.finderecipe.feature.presentation.main.detail.components

import android.webkit.WebView
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.domain.model.recipe_detail.RecipeDetail
import com.halilkrkn.finderecipe.domain.model.recipe_detail.Step
import com.halilkrkn.finderecipe.domain.model.similar_recipe.SimilarRecipe
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar

@Composable
fun SimilarMenuSheet(
    modifier: Modifier = Modifier,
    similarRecipe: SimilarRecipe,
) {
    val verticalScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(verticalScrollState)
            .wrapContentSize()
    ) {
        SimilarMenuSourceUrl(
            similarRecipe = similarRecipe,
        )
    }
}

@Composable
fun SimilarMenuSourceUrl(
    similarRecipe: SimilarRecipe,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    loadUrl(similarRecipe.sourceUrl)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

