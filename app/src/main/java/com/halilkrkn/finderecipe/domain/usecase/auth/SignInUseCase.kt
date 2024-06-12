package com.halilkrkn.finderecipe.domain.usecase.auth

import com.google.firebase.auth.AuthResult
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val findeRecipeAuthRepository: FindeRecipeAuthRepository,
) {
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        val authResult = findeRecipeAuthRepository.signInWithEmailAndPassword(email, password)
        emit(Resource.Success(authResult))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }
}
