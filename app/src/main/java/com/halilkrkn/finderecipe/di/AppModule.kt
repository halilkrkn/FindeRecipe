package com.halilkrkn.finderecipe.di

import com.halilkrkn.finderecipe.data.local.db.FindeRecipeDatabase
import com.halilkrkn.finderecipe.data.remote.api.FindeRecipeApi
import com.halilkrkn.finderecipe.data.repository.FindeRecipeRepositoryImpl
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeRepository
import com.halilkrkn.finderecipe.domain.usecase.recipe.FindeRecipeUseCases
import com.halilkrkn.finderecipe.domain.usecase.recipe.GetAllRecipesUseCase
import com.halilkrkn.finderecipe.domain.usecase.recipe.GetFindeRecipeFavoriteUseCase
import com.halilkrkn.finderecipe.domain.usecase.recipe.GetMealTypeRecipesUseCase
import com.halilkrkn.finderecipe.domain.usecase.recipe.GetRecipeDetailUseCase
import com.halilkrkn.finderecipe.domain.usecase.recipe.GetSearchRecipesUseCase
import com.halilkrkn.finderecipe.domain.usecase.recipe.GetSimilarRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideFindeRecipeRepository(
        api: FindeRecipeApi,
        db: FindeRecipeDatabase,
    ): FindeRecipeRepository {
        return FindeRecipeRepositoryImpl(api, db)
    }

    @Singleton
    @Provides
    fun provideFindeRecipeUseCases(repository: FindeRecipeRepository): FindeRecipeUseCases {
        return FindeRecipeUseCases(
            getAllRecipesUseCase = GetAllRecipesUseCase(repository),
            getMealTypeRecipesUseCase = GetMealTypeRecipesUseCase(repository),
            getSearchRecipesUseCase = GetSearchRecipesUseCase(repository),
            getRecipeDetailUseCase = GetRecipeDetailUseCase(repository),
            getSimilarRecipesUseCase = GetSimilarRecipesUseCase(repository),
            getFindeRecipeFavoriteUseCase = GetFindeRecipeFavoriteUseCase(repository)
        )
    }
}