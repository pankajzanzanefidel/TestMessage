package com.example.testmessage.testmessageapp.utils;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.example.testmessage.testmessageapp.R;
import com.example.testmessage.testmessageapp.helper.Constants;

public class NotificationBuilder {

    private Notification notification = null;
    private Context context = null;
    private String title = null;
    private String message = null;
    private PendingIntent pedingIntent = null;
    private int icon = 0;
    private boolean isVibrateOn = false;


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public PendingIntent getPedingIntent() {
        return pedingIntent;
    }

    public void setPendingIntent(PendingIntent pedingIntent) {
        this.pedingIntent = pedingIntent;
    }

    public boolean isVibrateOn() {
        return isVibrateOn;
    }

    public void setVibrateOn(boolean vibrateOn) {
        isVibrateOn = vibrateOn;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification build() {
        if (AndroidVersionUtil.isBeforeOreo()) {

            int defaultFlag = isVibrateOn ? (Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND) : Notification.DEFAULT_SOUND;

            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            inboxStyle.addLine(message);
            bigTextStyle.bigText(message);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Constants.NOTIFICATION.NOTIFICATION_CHANNEL_GLOBAL)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setStyle(bigTextStyle)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setSmallIcon(icon > 0 ? icon : R.mipmap.ic_launcher)
                    .setDefaults(defaultFlag)
                    .setContentIntent(pedingIntent)
                    .setAutoCancel(true);


            notification = builder.build();
        } else {

            CharSequence charSeq = "Greenforce";
            String notificationChannelId = Constants.NOTIFICATION.NOTIFICATION_CHANNEL_GLOBAL;

            NotificationChannel channel = new NotificationChannel(notificationChannelId,
                    charSeq,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(isVibrateOn);
            channel.setVibrationPattern(new long[]{500, 500});

            ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification.Builder builder = new Notification.Builder(context, Constants.NOTIFICATION.NOTIFICATION_CHANNEL_GLOBAL)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(icon > 0 ? icon : R.mipmap.ic_launcher)
                    .setContentIntent(pedingIntent)
                    .setChannelId(notificationChannelId)
                    .setAutoCancel(true);

            notification = builder.build();


        }

        return notification;
    }
}
