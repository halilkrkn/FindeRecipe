package com.halilkrkn.finderecipe.feature.presentation.main.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halilkrkn.finderecipe.core.util.DetailRecipeEvents
import com.halilkrkn.finderecipe.domain.model.recipe_detail.RecipeDetail

@Composable
fun DetailRecipeEvent(
    recipeDetail: RecipeDetail,
) {

    val detailEvents = DetailRecipeEvents(recipeDetail = recipeDetail)
    val events = listOf(
        detailEvents.events
    ).first()

    Spacer(modifier = Modifier.height(16.dp))
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(events) { detailRecipeEvent ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        color = Color.Transparent
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(32.dp),
                    painter = painterResource(id = detailRecipeEvent.icon),
                    contentDescription = "icon"
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = detailRecipeEvent.text,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = detailRecipeEvent.result,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                    color = Color.Black
                )
            }
        }
    }
}