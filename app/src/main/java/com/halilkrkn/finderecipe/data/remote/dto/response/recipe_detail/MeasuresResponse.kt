package com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail


import com.google.gson.annotations.SerializedName

data class MeasuresResponse(
    @SerializedName("metric")
    val metric: MetricResponse,
    @SerializedName("us")
    val us: UsResponse
)