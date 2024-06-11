package com.halilkrkn.finderecipe.data.repository

import com.halilkrkn.finderecipe.data.local.db.FindeRecipeDatabase
import com.halilkrkn.finderecipe.data.local.entity.NotificationEntity
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeNotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindeRecipeNotificationRepositoryImpl @Inject constructor(
    private val db: FindeRecipeDatabase
) : FindeRecipeNotificationRepository {
    override fun getAllNotificationRecipes(userId: String): Flow<List<NotificationEntity>> {
        return db.notificationDao().getAllNotifications(userId = userId)
    }
}