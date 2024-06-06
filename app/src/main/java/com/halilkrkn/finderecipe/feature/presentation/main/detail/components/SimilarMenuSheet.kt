package com.halilkrkn.finderecipe.feature.presentation.main.detail.components

import android.webkit.WebView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun SimilarMenuSheet(
    modifier: Modifier = Modifier,
    similarRecipeSourceUrl: MutableState<String> = remember { mutableStateOf("") },
    ) {
    val verticalScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(verticalScrollState)
            .wrapContentSize()
    ) {
        SimilarMenuSourceUrl(
            similarRecipeSourceUrl = similarRecipeSourceUrl,
        )
    }
}

@Composable
fun SimilarMenuSourceUrl(
    similarRecipeSourceUrl: MutableState<String> = remember { mutableStateOf("") },
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
                    loadUrl(similarRecipeSourceUrl.value)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

