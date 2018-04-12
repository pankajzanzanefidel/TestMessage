package com.example.testmessage.testmessageapp.utils;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

public class UtilNotification {

    public static void showNotification(Context context, Notification notification, int id){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);
    }

}
