package com.halilkrkn.finderecipe.data.local.dao

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.halilkrkn.finderecipe.data.local.db.FindeRecipeDatabase
import com.halilkrkn.finderecipe.data.local.entity.NotificationEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
class NotificationDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("finde_recipe_db_test")
    lateinit var database: FindeRecipeDatabase

    private lateinit var dao: NotificationDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.notificationDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun givenNotification_whenInsertNotification_thenGetAllNotifications() = runBlocking {
        // Given
        val notificationEntity = NotificationEntity(
            id = 1,
            title = "The Movie",
            userId = "userId",
            image = "image",
            imageType = "jpg",
        )


        // When
        dao.insertNotification(notificationEntity)

        // Then
        dao.getAllNotifications(notificationEntity.userId).first().let { recipes ->
            Truth.assertThat(recipes).contains(notificationEntity)
        }
    }
}