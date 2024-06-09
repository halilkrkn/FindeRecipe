package com.halilkrkn.finderecipe.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.halilkrkn.finderecipe.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FindeRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoriteRecipe: RecipeEntity)

    @Delete
    suspend fun deleteFavoriteRecipe(favoriteRecipe: RecipeEntity)

//    @Query("SELECT * FROM movies_favorite WHERE userId = :userId")
//    fun getAllFavorite(recipeId: String): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipes  WHERE userId = :userId ORDER BY id ASC ")
    fun getAllFavoriteRecipes(userId: String): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE title LIKE :searchQuery || '%'")
    fun searchFavoriteRecipe(searchQuery: String): Flow<List<RecipeEntity>>
}