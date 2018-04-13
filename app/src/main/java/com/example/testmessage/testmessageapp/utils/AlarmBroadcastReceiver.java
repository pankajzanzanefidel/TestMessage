package com.example.testmessage.testmessageapp.utils;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.testmessage.testmessageapp.R;
import com.example.testmessage.testmessageapp.database.DatabaseHouse;
import com.example.testmessage.testmessageapp.database.dao.MessageDao;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelMessage;
import com.example.testmessage.testmessageapp.enums.EnumMessageState;
import com.example.testmessage.testmessageapp.executor.AppExecutor;

import java.util.List;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        UtilsAlarmManager.setRepeatingNotification(context);
        AppExecutor.getINSTANCE().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {

                MessageDao messageDao = DatabaseHouse.getSingleTon(context).getMessageDao();
                List<DbModelMessage> list = messageDao.getListMessages(EnumMessageState.SENT.ordinal());
                Log.d("WASTE","Sent messages: "+((list == null)?"0":list.size()));

                if(list == null || list.size()<=0){
                    return;
                }

                final StringBuilder builder = new StringBuilder();

                for(DbModelMessage model: list){
                    model.setState(EnumMessageState.NOTIFIED.ordinal());
                    messageDao.update(model);

                    builder.append(model.getNumbers());
                    builder.append(System.getProperty("line.separator"));
                    builder.append(model.getText());
                    builder.append(System.getProperty("line.separator"));

                    Log.d("WASTE","numbers: "+model.getNumbers());
                    Log.d("WASTE","text: "+model.getText());

                }

                AppExecutor.getINSTANCE().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        showMessage(context,builder.toString());
                    }
                });

            }
        });
    }

    @SuppressLint("NewApi")
    private void showMessage(Context context, String message){

        NotificationBuilder builder = new NotificationBuilder();
        builder.setContext(context);

        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setMessage(message);
        builder.setTitle("Message Sent To:");
        builder.setVibrateOn(true);
        UtilNotification.showNotification(context, builder.build(), 1);

    }
}
