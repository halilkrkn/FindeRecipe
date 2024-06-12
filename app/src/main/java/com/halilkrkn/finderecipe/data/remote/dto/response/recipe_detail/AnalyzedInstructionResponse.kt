package com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail


import com.google.gson.annotations.SerializedName

data class AnalyzedInstructionResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("steps")
    val steps: List<StepResponse>
)