package com.halilkrkn.finderecipe.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.halilkrkn.finderecipe.data.local.dao.FindeRecipeDao
import com.halilkrkn.finderecipe.data.local.entity.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FindeRecipeDatabase: RoomDatabase() {
    abstract fun recipeDao(): FindeRecipeDao
}