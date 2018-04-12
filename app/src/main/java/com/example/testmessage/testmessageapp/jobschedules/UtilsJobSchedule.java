package com.example.testmessage.testmessageapp.jobschedules;


import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.example.testmessage.testmessageapp.enums.EnumJobType;
import com.example.testmessage.testmessageapp.utils.UtilUniqueJobId;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class UtilsJobSchedule {

    public JobInfo createSmsJobSchedule(Context mContext, String message, List<String> listNumbers, long delayInMillis){

        int mJobId = UtilUniqueJobId.getNextJobId(mContext);
        PersistableBundle bundle =new PersistableBundle();
        bundle.putInt("JOBID",mJobId);
        bundle.putString("MESSAGE",message);
        bundle.putInt("JOBTYPE", EnumJobType.SENDMESSAGE.ordinal());
        bundle.putStringArray("CONTACT_NUMBER",listNumbers.toArray(new String[]{}));

        JobInfo.Builder builder = new JobInfo.Builder(mJobId,new ComponentName(mContext,ServiceJobScheduler.class));
        builder.setMinimumLatency(delayInMillis);
        builder.setOverrideDeadline(delayInMillis);
        builder.setExtras(bundle);
        JobInfo jobInfo = builder.build();
        Log.d("WASTE","JobInfo:"+jobInfo.toString()+" delayInMillis: "+delayInMillis);

        return jobInfo;
    }

}
