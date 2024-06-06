package com.halilkrkn.finderecipe.feature.presentation.main.recipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar

@Composable
fun RecipeMealTypeSection(
    modifier: Modifier = Modifier,
    mealTypes: List<Recipe>,
    navController: NavController,
    isLoading: Boolean,
    error: String ?= null
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .size(120.dp)
                .padding(24.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            LoadingProgressBar(
                raw = R.raw.loading
            )
        }
    }

    if (mealTypes.isEmpty() && !isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoadingProgressBar(
                modifier = Modifier
                    .size(width = 200.dp, height = 200.dp),
                raw = R.raw.image_error
            )
            Text(
                text = if (mealTypes.isEmpty()) "No answer found for meal types!" else error ?: "No answer found for meal types",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(600.dp)
    ) {
        RecipeMealTypeItemScreen(
            mealTypes = mealTypes,
            navController = navController,
        )
    }
}