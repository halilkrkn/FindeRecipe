package com.halilkrkn.finderecipe.feature.presentation.auth.state

data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: String = "",
    val isError: String = ""
)
