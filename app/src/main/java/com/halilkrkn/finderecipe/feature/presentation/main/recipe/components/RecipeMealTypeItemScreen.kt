package com.halilkrkn.finderecipe.feature.presentation.main.recipe.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.halilkrkn.finderecipe.core.util.MealTypes


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedAnimatable")
@Composable
fun RecipeMealTypeItemScreen(
    isRefreshing: Boolean,
//    theMovies: List<TheTrendingMovies>,
    mealTypes: List<MealTypes>,

    ) {

    val pullRefreshState = rememberPullToRefreshState()


    Box(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            columns = GridCells.Fixed(2),
        ) {
            items(mealTypes) { mealType ->
                RecipeMealTypeItem(
                    theMeal = mealType,
                    modifier = Modifier.fillMaxWidth(),
                    onItemClick = {}
                )
            }
        }

//        PullToRefreshContainer(
//            state = pullRefreshState,
//            modifier = Modifier.align(Alignment.TopCenter),
//        )
    }
}