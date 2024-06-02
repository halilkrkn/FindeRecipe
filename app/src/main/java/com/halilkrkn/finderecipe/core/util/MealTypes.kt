package com.halilkrkn.finderecipe.core.util

sealed class MealTypes(val type: String) {
    data object MainCourse : MealTypes("main course")
    data object SideDish : MealTypes("side dish")
    data object Dessert : MealTypes("dessert")
    data object Appetizer : MealTypes("appetizer")
    data object Salad : MealTypes("salad")
    data object Bread : MealTypes("bread")
    data object Breakfast : MealTypes("breakfast")
    data object Soup : MealTypes("soup")
    data object Beverage : MealTypes("beverage")
    data object Sauce : MealTypes("sauce")
    data object Marinade : MealTypes("marinade")
    data object FingerFood : MealTypes("fingerfood")
    data object Snack : MealTypes("snack")
    data object Drink : MealTypes("drink")
}