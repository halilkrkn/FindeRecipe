@file:OptIn(ExperimentalMaterial3Api::class)

package com.halilkrkn.finderecipe.feature.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.halilkrkn.finderecipe.ui.theme.FloralWhite

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onClick: @Composable () -> Unit = {},
    onRefresh: () -> Unit = {},
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
        actions = {
            // actions
            IconButton(onClick = {
                onRefresh()
            }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription ="Refresh" )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = FloralWhite
        )
    )
}