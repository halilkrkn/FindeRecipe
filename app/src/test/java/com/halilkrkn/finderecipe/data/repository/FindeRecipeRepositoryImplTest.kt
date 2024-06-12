package com.halilkrkn.finderecipe.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.halilkrkn.finderecipe.data.local.db.FindeRecipeDatabase
import com.halilkrkn.finderecipe.data.local.entity.RecipeEntity
import com.halilkrkn.finderecipe.data.remote.api.FindeRecipeApi
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe.AllRecipeResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail.RecipeDetailResponse
import com.halilkrkn.finderecipe.data.remote.dto.response.similar_recipe.SimilarRecipeResponse
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class FindeRecipeRepositoryImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var findeRecipeRepository: FindeRecipeRepository
    private lateinit var findeRecipeApi: FindeRecipeApi
    private lateinit var findeRecipeDatabase: FindeRecipeDatabase

    @Before
    fun setup() {
        findeRecipeApi = mockk()
        findeRecipeDatabase = mockk()

        findeRecipeRepository = FindeRecipeRepositoryImpl(findeRecipeApi, findeRecipeDatabase)
    }


    @Test
    fun `given All Recipe when getRecipes then return AllRecipeResponse`() = runTest {
        // Given
        val allRecipeResponse = mockk<Response<AllRecipeResponse>>()

        // When
        coEvery { findeRecipeApi.getRecipes() } returns allRecipeResponse

        // Then
        val result = findeRecipeRepository.getRecipes()
        assertThat(result).isEqualTo(allRecipeResponse)
    }

    @Test
    fun `given id when getRecipeDetail then return RecipeDetailResponse`() = runTest {
        // Given
        val recipeDetail = mockk<Response<RecipeDetailResponse>>()
        val id = 1

        // When
        coEvery { findeRecipeApi.getRecipeDetail(id) } returns recipeDetail

        // Then
        val result = findeRecipeRepository.getRecipeDetail(id)
        assertThat(result).isEqualTo(recipeDetail)
    }

    @Test
    fun `given mealType when getMealTypeRecipes then return AllRecipeResponse`() = runTest {
        // Given
        val allRecipeResponse = mockk<Response<AllRecipeResponse>>()
        val mealType = "drink"

        // When
        coEvery { findeRecipeApi.getMealTypeRecipes(mealType) } returns allRecipeResponse

        // Then
        val result = findeRecipeRepository.getMealTypeRecipes(mealType)
        assertThat(result).isEqualTo(allRecipeResponse)
    }

    @Test
    fun `given searchQuery when searchRecipes then return AllRecipeResponse`() = runTest {
        // Given
        val allRecipeResponse = mockk<Response<AllRecipeResponse>>()
        val searchQuery = "fish"

        // When
        coEvery { findeRecipeApi.getSearchRecipes(searchQuery) } returns allRecipeResponse

        // Then
        val result = findeRecipeRepository.getSearchRecipe(searchQuery)
        assertThat(result).isEqualTo(allRecipeResponse)
        }

    @Test
    fun `given id when getSimilarRecipes then return SimilarRecipeResponse`() = runTest {
        // Given
        val similarRecipeResponse = mockk<Response<List<SimilarRecipeResponse>>>()
        val id = 1

        // When
        coEvery { findeRecipeApi.getSimilarRecipes(id) } returns similarRecipeResponse

        // Then
        val result = findeRecipeRepository.getSimilarRecipes(id)
        assertThat(result).isEqualTo(similarRecipeResponse)
    }

    @Test
    fun `given recipe when insertFavoriteRecipe then insert recipe`() = runTest {
        // Given
        val recipe = RecipeEntity(
            id = 7103,
            userId = "agam",
            image = "prompta",
            imageType = "sodales",
            title = "animal"
        )

        // When
        coEvery { findeRecipeDatabase.recipeDao().insertFavoriteRecipe(recipe) } returns Unit

        // Then
        val result = findeRecipeRepository.insertFavoriteRecipe(recipe)
        assertThat(result).isEqualTo(Unit)
    }

    @Test
    fun `given recipe when deleteFavoriteRecipe then delete recipe`() = runTest {
        // Given
        val recipe = RecipeEntity(
            id = 7103,
            userId = "agam",
            image = "prompta",
            imageType = "sodales",
            title = "animal"
        )

        // When
        coEvery { findeRecipeDatabase.recipeDao().deleteFavoriteRecipe(recipe) } returns Unit

        // Then
        val result = findeRecipeRepository.deleteFavoriteRecipe(recipe)
        assertThat(result).isEqualTo(Unit)
    }

    @Test
    fun `given userId when getAllFavoriteRecipes then return RecipeEntity List `() {
        // Given
        val userId = "agam"

        // When
        coEvery { findeRecipeDatabase.recipeDao().getAllFavoriteRecipes(userId) } returns mockk()

        // Then
        val result = findeRecipeRepository.getAllFavoriteRecipes(userId)
        assertThat(result).isNotNull()
    }

    @Test
    fun `given searchQuery when searchFavoriteRecipe then return RecipeEntity List `() {
        // Given
        val searchQuery = "animal"

        // When
        coEvery { findeRecipeDatabase.recipeDao().searchFavoriteRecipe(searchQuery) } returns mockk()

        // Then
        val result = findeRecipeRepository.searchFavoriteRecipe(searchQuery)
        assertThat(result).isNotNull()
    }
}