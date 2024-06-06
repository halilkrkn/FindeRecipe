package com.halilkrkn.finderecipe.feature.presentation.main.recipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halilkrkn.finderecipe.ui.theme.DarkMidnightBlue

@Composable
fun CategoryInfoSection(
    modifier: Modifier = Modifier,
    title: String,
    secondTitle: String? = null,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        TextButton(onClick = {
            onClick()
        }) {
            Text(
                text = secondTitle ?: "",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = DarkMidnightBlue
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}