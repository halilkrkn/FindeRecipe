@file:OptIn(ExperimentalMaterial3Api::class)

package com.halilkrkn.finderecipe.feature.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.halilkrkn.finderecipe.ui.theme.FloralWhite

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onClick: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                modifier = modifier,
                text = title
            )
        },
        navigationIcon = {
            onClick()
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = FloralWhite
        )
    )
}