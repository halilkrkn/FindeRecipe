package com.halilkrkn.finderecipe.feature.presentation.auth.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    state: Boolean,
    text: String,
    loadingText: String,
    iconContentDescription: String,
    shape: Shape = CircleShape,
    borderColor: Color = Color.Green,
    clickBorderColor: Color = Color.Red,
    clickProgressBarColor: Color = Color.Yellow,
    buttonColors: ButtonColors,
    icon: Painter,
) {

    var clicked by remember { mutableStateOf(false) }
    Button(
        onClick = {
            clicked = !clicked
            onClick()
        },
        shape = shape,
        border = BorderStroke(
            width = 2.dp,
            color = if (clicked) borderColor else clickBorderColor,
        ),
        colors = buttonColors,
        modifier = modifier
            .heightIn(50.dp)
            .padding(horizontal = 36.dp)
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 4.dp,
                    bottom = 4.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = icon,
                contentDescription = iconContentDescription,
                modifier = Modifier
                    .height(28.dp)
            )
            Text(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                text = if (clicked) loadingText else text
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (state) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp),
                    strokeWidth = 2.dp,
                    color = clickProgressBarColor
                )
            }
        }
    }
}