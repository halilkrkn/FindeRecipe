package com.halilkrkn.finderecipe.domain.usecase.notification

import com.halilkrkn.finderecipe.core.resource.Resource
import com.halilkrkn.finderecipe.data.mappers.toRecipe
import com.halilkrkn.finderecipe.domain.model.recipe.Recipe
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeNotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotificationUseCase @Inject constructor(
    private val repository: FindeRecipeNotificationRepository,
) {
    fun getAllNotificationRecipes(userId: String): Flow<Resource<List<Recipe>>> = flow {
        emit(Resource.Loading())
        val response =
            repository.getAllNotificationRecipes(userId = userId).first().map { notificationEntity ->
                notificationEntity.toRecipe()
            }
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "An unexpected error occurred"))
    }
}