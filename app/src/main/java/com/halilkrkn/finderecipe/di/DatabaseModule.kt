package com.halilkrkn.finderecipe.di

import android.content.Context
import androidx.room.Room
import com.halilkrkn.finderecipe.data.local.db.FindeRecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideFindeRecipeDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        FindeRecipeDatabase::class.java,
        "recipe_db"
    ).build()
}