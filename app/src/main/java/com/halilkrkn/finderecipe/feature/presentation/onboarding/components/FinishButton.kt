package com.halilkrkn.finderecipe.feature.presentation.onboarding.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.halilkrkn.finderecipe.ui.theme.FloralWhiteCream

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButton(modifier: Modifier = Modifier, pagerState: PagerState, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = modifier
                .fillMaxWidth(),
            visible = pagerState.currentPage == 4
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = FloralWhiteCream,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(text = "Finish")
            }
        }
    }
}