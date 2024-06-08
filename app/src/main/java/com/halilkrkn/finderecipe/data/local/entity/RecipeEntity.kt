package com.halilkrkn.finderecipe.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String,
)