package com.halilkrkn.finderecipe.core.work_manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.halilkrkn.finderecipe.MainActivity
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.data.local.db.FindeRecipeDatabase
import com.halilkrkn.finderecipe.data.mappers.toNotificationEntity
import com.halilkrkn.finderecipe.data.remote.api.FindeRecipeApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltWorker
class ApiWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    @Assisted private val apiService: FindeRecipeApi,
    @Assisted private val db: FindeRecipeDatabase,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        var count : MutableStateFlow<Int> = MutableStateFlow(0)

        return try {
            val response = apiService.getRecipes()
            if (response.isSuccessful) {
                response.body()?.let { recipe ->
                    val message = recipe.recipeResponses.firstOrNull()
                    Log.d("RecipeWorker", "Id: ${message?.id}, Title: ${message?.title}")
                    if (message != null) {
                        db.notificationDao().insert(message.toNotificationEntity())
                    }
                }
                count.value++
                Result.success()
            } else {
                Result.retry()
            }
            sendNotification()
            Result.success()
        } catch (e: Exception) {
            Result.failure(Data.Builder().putString("error", e.message).build())
        }
    }

    private fun sendNotification() {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1
        val channelId = "new_recipes_channel"
        val channelName = "New Recipes"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val title = "Yeni Tarifler!"
        val message = "Yeni tarifler eklendi, göz atın! 🧐"

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.finde_recipe_round)
            .setContentTitle(title)
            .setContentText(message)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(ContextCompat.getColor(applicationContext, R.color.black))
            .setStyle(NotificationCompat.BigTextStyle().bigText(message).setBigContentTitle(title))
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationId, notification)
    }
}
