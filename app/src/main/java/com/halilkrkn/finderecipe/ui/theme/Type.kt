package com.halilkrkn.finderecipe.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.halilkrkn.finderecipe.R


val Montserrat = FontFamily(
    Font(R.font.montserrat_black, FontWeight.Black),
    Font(R.font.montserrat_black_italic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.montserrat_extra_bold, FontWeight.ExtraBold),
    Font(R.font.montserrat_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.montserrat_extra_light, FontWeight.ExtraLight),
    Font(R.font.montserrat_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.montserrat_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_semibold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.montserrat_thin, FontWeight.Thin),
    Font(R.font.montserrat_thin_italic, FontWeight.Thin, FontStyle.Italic)
)

private val defaultTypography = Typography()

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = Montserrat),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = Montserrat),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = Montserrat),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = Montserrat),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = Montserrat),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = Montserrat),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = Montserrat),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = Montserrat),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = Montserrat),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = Montserrat),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = Montserrat),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = Montserrat),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = Montserrat),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = Montserrat),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = Montserrat)
)