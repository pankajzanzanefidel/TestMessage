package com.example.testmessage.testmessageapp.jobschedules;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.text.TextUtils;
import android.util.Log;

import com.example.testmessage.testmessageapp.activities.HomeActivity;
import com.example.testmessage.testmessageapp.database.DatabaseHouse;
import com.example.testmessage.testmessageapp.database.dao.MessageDao;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelMessage;
import com.example.testmessage.testmessageapp.enums.EnumJobType;
import com.example.testmessage.testmessageapp.enums.EnumMessageState;
import com.example.testmessage.testmessageapp.executor.AppExecutor;
import com.example.testmessage.testmessageapp.utils.SmsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceJobScheduler extends JobService {

    @Override
    public boolean onStartJob(final JobParameters params) {

        if (params == null) {
            Log.d("WASTE", "No job params");
        }

        if (params.getExtras().getInt("JOBTYPE") == EnumJobType.SENDMESSAGE.ordinal()) {

            sendMessageJob(params);

        }

        /*
        Return true if you want job to keep running
        example, if you have any thread doing processing
        you need to call jobFinsihed after thread finishes its task
        otherwise app will consume battery
        */
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    private void sendMessageJob(final JobParameters params) {

        final int jobId = params.getJobId();
        String message = params.getExtras().getString("MESSAGE");
        String number = params.getExtras().getString("CONTACT_NUMBER");
        String timeInDelay = params.getExtras().getString("TIMEDELAY");
        if (TextUtils.isEmpty(number)) {

            return;
        }
        //  String[] arr = listNumbers.toArray(new String[]{});
        Log.d("JOB", "Params: " + params.getJobId() + " Numbers: " + number + " timedelay " + timeInDelay);

            SmsUtils smsUtils = new SmsUtils(null);
            smsUtils.sendMsg(getApplicationContext(), message, number);


        AppExecutor.getINSTANCE().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {

                MessageDao messageDao = DatabaseHouse.getSingleTon(getApplicationContext()).getMessageDao();
                DbModelMessage dbMessage = messageDao.getModelMessage(jobId);
                dbMessage.setState(EnumMessageState.SENT.ordinal());
                messageDao.update(dbMessage);

                AppExecutor.getINSTANCE().getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        jobFinished(params, false);
                    }
                });
            }
        });

    }
}
