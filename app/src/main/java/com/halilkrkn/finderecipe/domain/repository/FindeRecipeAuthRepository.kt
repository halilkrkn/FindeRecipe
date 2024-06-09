package com.halilkrkn.finderecipe.domain.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult

interface FindeRecipeAuthRepository {
    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult
    suspend fun signUpWithEmailAndPassword(email: String, password: String): AuthResult
    suspend fun forgotPasswordWithEmail(email: String)
    suspend fun signInWithGoogle(credential: AuthCredential): AuthResult
}