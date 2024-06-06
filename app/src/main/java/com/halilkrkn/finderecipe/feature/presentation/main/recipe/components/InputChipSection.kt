package com.halilkrkn.finderecipe.feature.presentation.main.recipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.halilkrkn.finderecipe.core.util.MealTypes
import com.halilkrkn.finderecipe.ui.theme.Copper

@Composable
fun InputChipSection(
    onClick: (String) -> Unit,
    mealTypes: List<MealTypes>,
) {
    val clickColor = remember { mutableStateOf(false) }
    LazyRow(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(
            mealTypes,
            key = { mealType -> mealType.type }
        ) { mealType ->
            SuggestionChip(
                shape = RoundedCornerShape(12.dp),
                border = SuggestionChipDefaults.suggestionChipBorder(
                    enabled = true,
                    borderColor = Copper,
                    borderWidth = 1.5f.dp
                ),
                modifier = Modifier.padding(horizontal = 6.dp),
                onClick = {
                    onClick(mealType.type)
                    clickColor.value = !clickColor.value
                },
                label = {
                    Text(
                        text = mealType.type,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            letterSpacing = MaterialTheme.typography.labelLarge.letterSpacing,
                            fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                        )
                    )
                }
            )
        }
    }
}