package com.halilkrkn.finderecipe.domain.repository

import com.halilkrkn.finderecipe.data.local.entity.NotificationEntity
import kotlinx.coroutines.flow.Flow

interface FindeRecipeNotificationRepository {
    fun getAllNotificationRecipes(userId: String): Flow<List<NotificationEntity>>
}