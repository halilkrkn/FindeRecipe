package com.halilkrkn.finderecipe.data.local.dao

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.halilkrkn.finderecipe.data.local.db.FindeRecipeDatabase
import com.halilkrkn.finderecipe.data.local.entity.RecipeEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
class FindeRecipeDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("finde_recipe_db_test")
    lateinit var database: FindeRecipeDatabase

    private lateinit var dao: FindeRecipeDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.recipeDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun givenRecipe_whenInsertRecipe_thenGetAllRecipe() = runBlocking {
        // Given
        val recipeEntity = RecipeEntity(
            id = 1,
            title = "The Movie",
            userId = "userId",
            image = "image",
            imageType = "jpg",
        )


        // When
        dao.insertFavoriteRecipe(recipeEntity)

        // Then
        dao.getAllFavoriteRecipes(recipeEntity.userId).first().let { recipes ->
            assertThat(recipes).contains(recipeEntity)
        }
    }

    @Test
    fun givenRecipe_whenDeleteRepice_thenGetAllRecipe() = runBlocking {
        // Given
        val recipeEntity = RecipeEntity(
            id = 1,
            title = "The Recipe",
            userId = "userId",
            image = "image",
            imageType = "jpg",
        )


        // When
        dao.insertFavoriteRecipe(recipeEntity)
        dao.deleteFavoriteRecipe(recipeEntity)

        // Then
        dao.getAllFavoriteRecipes(recipeEntity.userId).first().let { recipes ->
            assertThat(recipes).doesNotContain(recipeEntity)
        }
    }

    @Test
    fun givenRecipe_whenSearchRecipe_thenGetSearchedRecipe() = runBlocking {
        // Given
        val recipeEntity = RecipeEntity(
            id = 1,
            title = "The Recipe",
            userId = "userId",
            image = "image",
            imageType = "jpg",
        )


        // When
        dao.insertFavoriteRecipe(recipeEntity)

        // Then
        dao.searchFavoriteRecipe("The Recipe").first().let { recipes ->
            assertThat(recipes).contains(recipeEntity)
        }
    }

    @Test
    fun givenRecipe_whenSearchRecipe_thenGetNoSearchedRecipe() = runBlocking {
        // Given
        val recipeEntity = RecipeEntity(
            id = 1,
            title = "The Recipe",
            userId = "userId",
            image = "image",
            imageType = "jpg",
        )
        // When
        dao.insertFavoriteRecipe(recipeEntity)

        // Then
        dao.searchFavoriteRecipe("Recipe 2").first().let { recipes ->
            assertThat(recipes).doesNotContain(recipeEntity)
        }
    }

}