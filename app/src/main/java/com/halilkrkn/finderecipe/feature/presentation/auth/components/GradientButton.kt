package com.halilkrkn.finderecipe.feature.presentation.auth.components


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halilkrkn.finderecipe.ui.theme.DarkMidnightBlue
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import com.halilkrkn.finderecipe.ui.theme.Razzmatazz

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GradientButton(
    onClick: () -> Unit = {},
    nameButton: String,
    roundedCornerShape: RoundedCornerShape,
    state: Boolean
) {

    val gradientColor = listOf(Razzmatazz, DarkMidnightBlue )
    val cornerRadius = 16.dp

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        onClick = {
            //your code
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColor),
                    shape = roundedCornerShape
                )
                .clickable {
                    onClick()
                }
                .clip(roundedCornerShape)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = nameButton,
                fontSize = 20.sp,
                color = Color.White
            )

            if (state) {
                CircularProgressIndicator(
                    color = FloralWhite,
                    strokeWidth = 2.dp,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterEnd)
                )
            } else {
                Text(
                    text = nameButton,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

        }
    }
}