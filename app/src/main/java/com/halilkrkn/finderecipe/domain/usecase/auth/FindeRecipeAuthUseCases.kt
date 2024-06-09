package com.halilkrkn.finderecipe.domain.usecase.auth

data class FindeRecipeAuthUseCases(
    val signInUseCase: SignInUseCase,
    val signUpUseCase: SignUpUseCase,
    val forgotPasswordUseCase: ForgotPasswordUseCase,
    val googleSignInUseCase: GoogleSignInUseCase,
)
