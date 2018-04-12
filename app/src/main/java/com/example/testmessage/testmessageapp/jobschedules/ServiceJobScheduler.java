package com.example.testmessage.testmessageapp.jobschedules;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.example.testmessage.testmessageapp.utils.SmsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceJobScheduler extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {

        if(params == null){
            Log.d("WASTE","No job params");
        }

        String message = params.getExtras().getString("MESSAGE");
        List<String> listNumbers =Arrays.asList(params.getExtras().getStringArray("CONTACT_NUMBER"));
        Log.d("WASTE","Params: "+params.getJobId() + " bundle: "+(params.getExtras()!=null?(params.getExtras().toString()):" No bundle"));

        SmsUtils smsUtils = new SmsUtils(null);
        smsUtils.sendMsg(getApplicationContext(),message,listNumbers);

        /*
        Return true if you want job to keep running
        example, if you have any thread doing processing
        you need to call jobFinsihed after thread finishes its task
        otherwise app will consume battery
        */
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
