package com.example.sujal.pocketmed;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

/**
 * Created by sujal on 6/2/2017.
 */
public class ClockActivity extends WakefulBroadcastReceiver {


    private static final int MY_NOTIFICATION_ID = 1;
    NotificationManager notificationManager;
    Notification myNotification;
    static MediaPlayer mp;
     PendingIntent pendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {


        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        mp=MediaPlayer.create(context,uri);
        mp.start();
        Toast.makeText(context.getApplicationContext(), "alarm started", Toast.LENGTH_LONG).show();

        Intent myIntent = new Intent(context, Edit.class);

        pendingIntent = PendingIntent.getActivity(
                context,
                0,
                myIntent, 0
        );
        myNotification = new NotificationCompat.Builder(context)
                .setContentTitle("Medicine Reminder!")
                .setContentText("Please take your medicine in time")
                .setTicker("Notification!")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setColor(134)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.notification)
                .build();

        notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
    }
}
