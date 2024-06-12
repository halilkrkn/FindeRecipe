package com.halilkrkn.finderecipe.di

import com.google.firebase.auth.FirebaseAuth
import com.halilkrkn.finderecipe.data.repository.FindeRecipeAuthRepositoryImpl
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeAuthRepository
import com.halilkrkn.finderecipe.domain.usecase.auth.FindeRecipeAuthUseCases
import com.halilkrkn.finderecipe.domain.usecase.auth.ForgotPasswordUseCase
import com.halilkrkn.finderecipe.domain.usecase.auth.GoogleSignInUseCase
import com.halilkrkn.finderecipe.domain.usecase.auth.SignInUseCase
import com.halilkrkn.finderecipe.domain.usecase.auth.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesFindeRecipeAuthRepository(
        firebaseAuth: FirebaseAuth,
    ): FindeRecipeAuthRepository {
        return FindeRecipeAuthRepositoryImpl(firebaseAuth)
    }

    @Singleton
    @Provides
    fun provideFindeRecipeAuthUseCases(findeRecipeAuthRepository: FindeRecipeAuthRepository): FindeRecipeAuthUseCases {
        return FindeRecipeAuthUseCases(
            signInUseCase = SignInUseCase(findeRecipeAuthRepository),
            signUpUseCase = SignUpUseCase(findeRecipeAuthRepository),
            forgotPasswordUseCase = ForgotPasswordUseCase(findeRecipeAuthRepository),
            googleSignInUseCase = GoogleSignInUseCase(findeRecipeAuthRepository)
        )
    }
}