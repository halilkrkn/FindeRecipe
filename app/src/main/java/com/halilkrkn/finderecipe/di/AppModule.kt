package com.halilkrkn.finderecipe.di

import com.halilkrkn.finderecipe.data.remote.api.FindeRecipeApi
import com.halilkrkn.finderecipe.data.repository.FindeRecipeRepositoryImpl
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeRepository
import com.halilkrkn.finderecipe.domain.usecase.FindeRecipeUseCases
import com.halilkrkn.finderecipe.domain.usecase.GetAllRecipesUseCase
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
    ): FindeRecipeRepository {
        return FindeRecipeRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideFindeRecipeUseCases(repository: FindeRecipeRepository): FindeRecipeUseCases {
        return FindeRecipeUseCases(
            getAllRecipesUseCase = GetAllRecipesUseCase(repository)
        )
    }
}