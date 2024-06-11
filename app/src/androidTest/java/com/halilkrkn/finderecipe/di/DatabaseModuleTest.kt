package com.halilkrkn.finderecipe.di

import android.content.Context
import androidx.room.Room
import com.halilkrkn.finderecipe.data.local.db.FindeRecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModuleTest {

    @Provides
    @Named("finde_recipe_db_test")
    fun provideInMemoryFindeRecipeDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context,
            FindeRecipeDatabase::class.java
        ).build()

}