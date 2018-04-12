package com.example.testmessage.testmessageapp.utils;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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

                for(DbModelMessage model: list){
                    model.setState(EnumMessageState.NOTIFIED.ordinal());
                    messageDao.update(model);
                }

            }
        });
    }
}
