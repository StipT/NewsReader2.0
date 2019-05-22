package com.tstipanic.factorynews_kotlin.common

import android.content.ContentValues.TAG
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.tstipanic.factorynews_kotlin.R

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage?) {
        super.onMessageReceived(p0)

        if (p0?.data != null) {
            val title = p0.data["title"]
            val body = p0.data["body"]

            val notification = NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24px)
                .build()

            val notificationManager = NotificationManagerCompat
                .from(applicationContext)

            notificationManager.notify(0, notification)

            // Check if message contains a notification payload.
            p0?.notification?.let {
                Log.d(TAG, "Message Notification Body: ${it.body}")
            }

            // Also if you intend on generating your own notifications as a result of a received FCM
            // message, here is where that should be initiated. See sendNotification method below.
        }
    }
}
