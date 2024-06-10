package com.halilkrkn.finderecipe.core.util

import androidx.annotation.DrawableRes
import com.halilkrkn.finderecipe.R

sealed class OnboardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    data object First : OnboardingPage(
        image = R.drawable.recipe_7,
        title = "İçeriklere ve Kategorilere Göre Arama Yapın",
        description = "İstediğiniz yemek tarifini kolayca bulun. İçeriklere ve kategorilere göre arama yapın."
    )

    data object Second : OnboardingPage(
        image = R.drawable.recipe_8,
        title = "En Beğenilen Tarifleri Keşfedin",
        description = "En beğenilen tarifleri keşfedin ve deneyin. En popüler tarifleri keşfedin ve deneyin."
    )

    data object Third : OnboardingPage(
        image = R.drawable.recipe_4,
        title = "Görsellerle Desteklenen Detaylı Tarifler",
        description = "Her tarifi adım adım takip edin, her seferinde mükemmel sonuçlar elde edin. Her aşamayı görsellerle takip edin, tariflerin daha kolay anlaşılmasını sağlayın."
    )

    data object Fourth : OnboardingPage(
        image = R.drawable.recipe_1,
        title = "Beğendiğiniz Tarifleri Kaydedin",
        description = "Beğendiğiniz tarifleri kaydedin ve daha sonra kolayca erişin. Favori tariflerinizi saklayın."
    )

    data object Fifth : OnboardingPage(
        image = R.drawable.recipe_3,
        title = "Haydi Mükemmel Yemekler Yapmaya Başlayalım!",
        description = "Artık yemek tariflerine kolayca ulaşabilirsiniz. Hemen başlayın ve mükemmel yemekler yapın."
    )
}