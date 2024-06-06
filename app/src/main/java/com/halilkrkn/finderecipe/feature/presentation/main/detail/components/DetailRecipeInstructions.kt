package com.halilkrkn.finderecipe.feature.presentation.main.detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halilkrkn.finderecipe.domain.model.recipe_detail.RecipeDetail
import com.halilkrkn.finderecipe.ui.theme.Coral
import com.halilkrkn.finderecipe.ui.theme.FloralWhite

@Composable
fun DetailRecipeInstructions(
    recipeDetail: RecipeDetail,
    showBottomSheet: MutableState<Boolean> = remember { mutableStateOf(false) },
) {
    Row(modifier = Modifier.wrapContentWidth()) {
        Text(
            text = "Instructions",
            fontSize = 28.sp,
            fontWeight = FontWeight.W500,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
    }
    Column {
        Text(
            text = recipeDetail.title, modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(modifier = Modifier
            .wrapContentWidth()
            .clickable {
                showBottomSheet.value = true
            }
            .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(Coral)) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "View All Steps",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500,
                        color = FloralWhite
                    )
                }
            }
        }
    }
}