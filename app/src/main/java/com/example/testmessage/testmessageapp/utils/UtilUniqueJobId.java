package com.example.testmessage.testmessageapp.utils;

import android.content.Context;

import com.example.testmessage.testmessageapp.helper.PreferenceUtils;

public class UtilUniqueJobId {

    public static int getNextJobId(Context mContext){

        PreferenceUtils pref = PreferenceUtils.getINSTANCE(mContext);
        int jobId = pref.getInteger(PreferenceUtils.PREF_JOB_ID);
        pref.putInteger(PreferenceUtils.PREF_JOB_ID,++jobId);

        return jobId;
    }
}
