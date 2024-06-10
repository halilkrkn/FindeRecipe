package com.halilkrkn.finderecipe.feature.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.halilkrkn.finderecipe.core.util.OnboardingPage
import com.halilkrkn.finderecipe.ui.theme.FloralWhite

@Composable
fun PagerScreen(onboardingPage: OnboardingPage) {

    Column(
        modifier = Modifier
            .background(
                color = FloralWhite
            )
            .fillMaxWidth()
    ) {

        Image(
            painter = painterResource(onboardingPage.image),
            contentDescription = "Onboarding Recipe Image",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(400.dp)
                .width(500.dp),
            contentScale = ContentScale.Fit

        )

        Column(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .padding(6.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = onboardingPage.title,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = onboardingPage.description,
                fontSize = MaterialTheme.typography.subtitle2.fontSize,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
        }
    }
}