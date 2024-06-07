package com.halilkrkn.finderecipe.feature.presentation.main.favorite.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(
    dismissState: SwipeToDismissBoxState,
) {
    val direction = dismissState.dismissDirection ?: return

    val color = when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd -> Color.Transparent
        SwipeToDismissBoxValue.EndToStart -> Red
        SwipeToDismissBoxValue.Settled -> TODO()
    }
    val scale by animateFloatAsState(
        if (
            dismissState.targetValue == SwipeToDismissBoxValue.Settled
        ) 0.75f else 1f,
        label = ""
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
            .fillMaxSize()
            .background(color),
    ) {
        if (direction == SwipeToDismissBoxValue.EndToStart) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.Black,
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .scale(scale)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}