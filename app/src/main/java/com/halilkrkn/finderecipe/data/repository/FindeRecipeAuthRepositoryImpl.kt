package com.halilkrkn.finderecipe.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FindeRecipeAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : FindeRecipeAuthRepository {
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): AuthResult {
        return firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String,
    ): AuthResult {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun forgotPasswordWithEmail(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).await()
    }

    override suspend fun signInWithGoogle(credential: AuthCredential): AuthResult {
        return firebaseAuth.signInWithCredential(credential).await()
    }
}