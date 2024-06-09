package com.halilkrkn.finderecipe.domain.usecase.auth

import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val findeRecipeAuthRepository: FindeRecipeAuthRepository,
) {
    suspend fun forgotPasswordWithEmail(email: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val authResult = findeRecipeAuthRepository.forgotPasswordWithEmail(email)
        emit(Resource.Success(authResult))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }
}
