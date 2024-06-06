package com.halilkrkn.finderecipe.feature.presentation.main.recipe

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.core.util.MealTypes
import com.halilkrkn.finderecipe.domain.model.recipe.MealTypeRecipe
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.feature.navigation.routes.DetailsRoutes
import com.halilkrkn.finderecipe.feature.presentation.components.CategoryInfoSection
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.feature.presentation.main.recipe.components.RecipeMealTypeItemScreen
import com.halilkrkn.finderecipe.ui.theme.Copper
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import com.halilkrkn.finderecipe.ui.theme.OxfordBlue
import kotlinx.coroutines.launch

@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecipeViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val state = viewModel.state.value
    val isLoading = state.isLoading
    val error = state.error
    val recipeList = state.recipeList

    val mealTypeRecipeList = viewModel.stateMealType.value.mealTypesList

    val mealTypes = listOf(
        MealTypes.Breakfast,
        MealTypes.Drink,
        MealTypes.Dessert,
        MealTypes.Soup,
        MealTypes.Salad,
        MealTypes.Bread,
        MealTypes.MainCourse,
        MealTypes.Appetizer,
        MealTypes.Beverage,
        MealTypes.Sauce,
        MealTypes.Marinade,
        MealTypes.FingerFood,
        MealTypes.Snack,
        MealTypes.SideDish
    )
    Scaffold(
        containerColor = FloralWhite,
        topBar = { TopBar() }
    ) { innerPadding ->

        val verticalScrollState = rememberScrollState()
        Column(
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(verticalScrollState)
                .fillMaxSize()
        ) {
            InfoSection()
            Spacer(modifier = Modifier.height(12.dp))
            CategoryInfoSection(
                title = "Recent Recipes",
                secondTitle = "See All",
                onClick = {
                    navController.navigate(DetailsRoutes.RecipeList.route)
                }
            )
            if (recipeList != null) {
                RecentRecipesListSection(
                    recipeList = recipeList,
                    isLoading = isLoading,
                    navController = navController
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            CategoryInfoSection(
                title = "Meal Types",
            )
            InputChipSection(
                onClick = { mealType ->
                    scope.launch {
                        Log.d("TAG", "InputChipSection: $mealType clicked")
                        viewModel.getMealTypeRecipes(mealType)
                    }
                },
                mealTypes = mealTypes,
            )
            Spacer(modifier = Modifier.height(12.dp))
            RecipeMealTypeSection(
                mealTypes = mealTypeRecipeList ?: emptyList(),
                navController = navController,
                isLoading = isLoading,
                error = error
            )
        }
    }
}

@Composable
fun RecipeMealTypeSection(
    modifier: Modifier = Modifier,
    mealTypes: List<MealTypeRecipe>,
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

@Composable
fun RecentRecipesListSection(
    modifier: Modifier = Modifier,
    recipeList: List<Recipe>,
    isLoading: Boolean,
    navController: NavController,
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .size(120.dp, 120.dp)
                .padding(24.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            LoadingProgressBar(
                raw = R.raw.loading
            )
        }
    }

    if (recipeList.isEmpty() && !isLoading) {
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
                text = if (recipeList.isEmpty()) "No answer found for meal types!" else "No answer found for meal types",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(recipeList) { recipe ->
            RecentRecipesItemSection(
                recipe = recipe,
                onClick = {
                    navController.navigate(DetailsRoutes.Detail.route.plus("/${recipe.id}"))
                }
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun RecentRecipesItemSection(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    onClick: (Recipe) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onClick(recipe)
                }
                .fillMaxWidth()
                .size(300.dp, 300.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SubcomposeAsyncImage(
                model = recipe.image,
                loading = {
                    Box(
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingProgressBar(
                            modifier = Modifier.size(100.dp, 100.dp),
                            raw = R.raw.loading
                        )
                    }
                },
                error = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = "Failed to load image"
                        )
                    }
                },
                contentDescription = recipe.title,
                modifier = Modifier
                    .size(312.dp, 231.dp)
                    .clip(RoundedCornerShape(36.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recipe.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .height(75.dp)

            )
        }
    }
}

@Composable
fun InfoSection(modifier: Modifier = Modifier) {
    Row {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Black,
                    )
                ) {
                    append("Taste Recipes \n")

                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight.Light,
                    )
                ) {
                    append("for you")
                }
            },
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.sp,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
        )

    }
}

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

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically

        ) {
            AsyncImage(
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .clip(RoundedCornerShape(12.dp)),
                model = "https://avatars.githubusercontent.com/u/42476890?v=4",
                contentDescription = "profile_image",
                placeholder = painterResource(id = R.drawable.baseline_person_24),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Hi, Halil 👋",
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
            )
        }

        BadgedBox(
            modifier = Modifier.padding(end = 8.dp),
            badge = {
                Badge {
                    Text(
                        text = "3",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Default.NotificationsNone,
                contentDescription = "Notifications",
                tint = OxfordBlue
            )
        }
    }
}


@Preview
@Composable
private fun RecipesScreenPreview() {
    RecipesScreen(navController = rememberNavController())
}