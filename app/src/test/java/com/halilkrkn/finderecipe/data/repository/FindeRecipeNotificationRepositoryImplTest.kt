package com.halilkrkn.finderecipe.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.halilkrkn.finderecipe.data.local.db.FindeRecipeDatabase
import com.halilkrkn.finderecipe.data.local.entity.NotificationEntity
import com.halilkrkn.finderecipe.domain.repository.FindeRecipeNotificationRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FindeRecipeNotificationRepositoryImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var findeRecipeNotificationRepository: FindeRecipeNotificationRepository
    private lateinit var findeRecipeDatabase: FindeRecipeDatabase

    @Before
    fun setup() {
        findeRecipeDatabase = mockk()

        findeRecipeNotificationRepository = FindeRecipeNotificationRepositoryImpl(findeRecipeDatabase)
    }


    @Test
    fun `given userId when getAllNotificationRecipes then return NotificationEntity List`() {
        // Given
        val userId = "1"
        val notificationEntityList = mockk<Flow<List<NotificationEntity>>>()

        // When
        coEvery { findeRecipeDatabase.notificationDao().getAllNotifications(userId = userId) } returns notificationEntityList

        // Then
        val result = findeRecipeNotificationRepository.getAllNotificationRecipes(userId)
        assertThat(result).isEqualTo(notificationEntityList)

    }


}