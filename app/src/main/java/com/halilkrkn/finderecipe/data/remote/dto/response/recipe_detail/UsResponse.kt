package com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail


import com.google.gson.annotations.SerializedName

data class UsResponse(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("unitLong")
    val unitLong: String,
    @SerializedName("unitShort")
    val unitShort: String
)