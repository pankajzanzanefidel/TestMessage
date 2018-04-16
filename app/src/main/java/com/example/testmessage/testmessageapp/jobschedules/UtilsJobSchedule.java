package com.example.testmessage.testmessageapp.jobschedules;


import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Log;

import com.example.testmessage.testmessageapp.database.dataenetities.DbModelContact;
import com.example.testmessage.testmessageapp.enums.EnumJobType;
import com.example.testmessage.testmessageapp.utils.UtilUniqueJobId;

public class UtilsJobSchedule {

    public JobInfo createSmsJobSchedule(Context mContext, String message, DbModelContact listNumbers, long delayInMillis){

        int mJobId = UtilUniqueJobId.getNextJobId(mContext);
        PersistableBundle bundle =new PersistableBundle();
        bundle.putInt("JOBID",mJobId);
        bundle.putString("MESSAGE",message);
        bundle.putInt("JOBTYPE", EnumJobType.SENDMESSAGE.ordinal());
        bundle.putString("CONTACT_NUMBER",listNumbers.getNumber());
        bundle.putString("TIMEDELAY",String.valueOf(delayInMillis));

        JobInfo.Builder builder = new JobInfo.Builder(mJobId,new ComponentName(mContext,ServiceJobScheduler.class));
        builder.setMinimumLatency(delayInMillis);
        builder.setOverrideDeadline(delayInMillis);
        builder.setExtras(bundle);
        JobInfo jobInfo = builder.build();
        Log.d("WASTE","JobInfo:"+jobInfo.toString()+" delayInMillis: "+delayInMillis);

        return jobInfo;
    }

}
