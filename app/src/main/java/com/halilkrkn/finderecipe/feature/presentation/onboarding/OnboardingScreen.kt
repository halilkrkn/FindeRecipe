@file:OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)

package com.halilkrkn.finderecipe.feature.presentation.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.halilkrkn.finderecipe.core.util.OnboardingPage
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs.AUTHENTICATION
import com.halilkrkn.finderecipe.feature.presentation.onboarding.components.FinishButton
import com.halilkrkn.finderecipe.feature.presentation.onboarding.components.PagerScreen
import com.halilkrkn.finderecipe.ui.theme.DarkMidnightBlue
import com.halilkrkn.finderecipe.ui.theme.FloralWhiteCream


@Composable
fun OnboardingScreen(
    navController: NavController,
) {
    val viewModel: OnboardingViewModel = hiltViewModel()
    val pages = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third,
        OnboardingPage.Fourth,
        OnboardingPage.Fifth
    )

    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { position ->
            PagerScreen(onboardingPage = pages[position])
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = Color.Red,
            inactiveColor = DarkMidnightBlue,
            indicatorWidth = 8.dp,
            indicatorHeight = 8.dp,
            spacing = 8.dp,
            modifier = Modifier
                .padding(30.dp)
                .align(Alignment.CenterHorizontally)
        )

        FinishButton(modifier = Modifier, pagerState = pagerState) {
            viewModel.saveOnBoardingState(completed = true)
            navController.popBackStack()
            navController.navigate(AUTHENTICATION)
        }
    }

}





