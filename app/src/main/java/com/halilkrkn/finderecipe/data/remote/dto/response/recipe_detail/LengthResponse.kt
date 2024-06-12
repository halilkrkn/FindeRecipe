package com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail


import com.google.gson.annotations.SerializedName

data class LengthResponse(
    @SerializedName("number")
    val number: Int,
    @SerializedName("unit")
    val unit: String
)