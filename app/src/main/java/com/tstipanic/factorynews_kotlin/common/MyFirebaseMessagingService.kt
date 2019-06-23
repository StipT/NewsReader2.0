package com.tstipanic.factorynews_kotlin.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.tstipanic.factorynews_kotlin.R
import com.tstipanic.factorynews_kotlin.map_screen.MapsActivity

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        var notificationId = 10
    }

    override fun onMessageReceived(p0: RemoteMessage?) {

        createNotificationChannel()

        if (p0!!.data.isNotEmpty()) {
            val notificationId = p0.messageId.toString()
            Log.d("onMessageReceived", notificationId)
            val notificationPayload = p0.data
            val messageData1 = notificationPayload[EXTRA_MESSAGING_DATA1]
            val messageData2 = notificationPayload[EXTRA_MESSAGING_DATA2]
            val messageTitle = notificationPayload[EXTRA_MESSAGING_TITLE]

            Log.d("MyFCMService", "messageData1 = " + messageData1.toString())
            Log.d("MyFCMService", "messageData2 = " + messageData2.toString())
            Log.d("MyFCMService", "messageTitle = " + messageTitle.toString())


            val intent = Intent(this, MapsActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra(EXTRA_MESSAGING_DATA1, messageData1)
                putExtra(EXTRA_MESSAGING_DATA2, messageData2)
                putExtra(EXTRA_MESSAGING_TITLE, messageTitle)
            }

            val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
            createNotification(pendingIntent)


        }
    }

    private fun createNotification(pendingIntent: PendingIntent) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24px)
            .setContentTitle("test konkatedrala test")
            .setContentText("Klikni da doznas gdje je")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setFullScreenIntent(pendingIntent, true)


        with(NotificationManagerCompat.from(this)) {
            notify(notificationId++, builder.build())
        }

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
