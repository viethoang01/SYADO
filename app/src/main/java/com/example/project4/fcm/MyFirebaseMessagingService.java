package com.example.project4.fcm;

import static com.example.project4.MyApplication.CHANNEL_ID;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.project4.MainActivity;
import com.example.project4.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {  // get data from firebase
        super.onMessageReceived(message);
            RemoteMessage.Notification notification = message.getNotification();
            if(notification == null){
                return;
            }
            String strTitle = notification.getTitle();
            String strMessage = notification.getBody();

            senNotification(strTitle,strMessage);
    }

    private void senNotification(String strTitle, String strMessage) {
        Intent intent = new Intent(this, MainActivity.class);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle(strTitle)
                .setContentText(strMessage)
                .setSmallIcon(R.drawable.app_logo)
                .setContentIntent(pendingIntent);

        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notification != null){
            notificationManager.notify(1,notification);
        }

    }
}
