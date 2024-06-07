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
    suspend fun insert(favoriteRecipe: RecipeEntity)

    @Delete
    suspend fun delete(favoriteRecipe: RecipeEntity)

//    @Query("SELECT * FROM movies_favorite WHERE userId = :userId")
//    fun getAllFavorite(recipeId: String): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipes")
    fun getAllFavorite(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE title LIKE :searchQuery || '%'")
    fun searchFavorite(searchQuery: String): Flow<List<RecipeEntity>>
}