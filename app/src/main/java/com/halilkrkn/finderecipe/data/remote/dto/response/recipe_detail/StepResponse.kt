package com.halilkrkn.finderecipe.data.remote.dto.response.recipe_detail


import com.google.gson.annotations.SerializedName

data class StepResponse(
    @SerializedName("equipment")
    val equipment: List<EquipmentResponse>,
    @SerializedName("ingredients")
    val ingredients: List<IngredientResponse>,
    @SerializedName("length")
    val length: LengthResponse,
    @SerializedName("number")
    val number: Int,
    @SerializedName("step")
    val step: String
)