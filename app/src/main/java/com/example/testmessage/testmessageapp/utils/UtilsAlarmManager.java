package com.example.testmessage.testmessageapp.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.example.testmessage.testmessageapp.activities.HomeActivity;

import java.util.Calendar;

public class UtilsAlarmManager {

    private static final int REQUEST_CODE = 1001;
    public static void setRepeatingNotification(Context mContext){

        AlarmManager alarmMgr = null;
        PendingIntent alarmIntent = null;

        alarmMgr = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(mContext, AlarmBroadcastReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(mContext, REQUEST_CODE, intent, 0);

        alarmMgr.setExact(AlarmManager.RTC_WAKEUP,
                Calendar.getInstance().getTimeInMillis()+(HomeActivity.PERIODIC_JOB_INTERVAL_SEC*1000),
                alarmIntent);

    }
}
