package com.halilkrkn.finderecipe.feature.presentation.auth.state

import com.google.firebase.auth.AuthResult

data class GoogleAuthState(
    val success: AuthResult? = null,
    val loading: Boolean = false,
    val error: String = ""
)