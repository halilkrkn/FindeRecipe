package com.halilkrkn.finderecipe.feature.presentation.main.detail.components

import android.webkit.WebView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar

@Composable
fun SimilarMenuSheet(
    modifier: Modifier = Modifier,
    similarRecipeSourceUrl: MutableState<String> = remember { mutableStateOf("") },
    isLoading: Boolean = false,
) {
    val verticalScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(verticalScrollState)
            .wrapContentSize()
    ) {
        SimilarMenuSourceUrl(
            similarRecipeSourceUrl = similarRecipeSourceUrl,
            isLoading = isLoading,
        )
    }
}

@Composable
fun SimilarMenuSourceUrl(
    similarRecipeSourceUrl: MutableState<String> = remember { mutableStateOf("") },
    isLoading: Boolean = false,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .size(120.dp)
                    .padding(24.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                LoadingProgressBar(
                    raw = R.raw.loading
                )
            }
        } else {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        loadUrl(similarRecipeSourceUrl.value)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

