package com.example.testmessage.testmessageapp.utils;

import android.content.Context;
import android.telephony.SmsManager;

import java.util.List;

public class SmsUtils {

    public interface ISmsSending {
        void onSmsSent();

        void onSmsFail();
    }

    private ISmsSending iSmsSending = null;

     public SmsUtils(ISmsSending iSmsSending) {
        this.iSmsSending = iSmsSending;
    }

    public void sendMsg(Context context, String msg, String listNumbers) {

        try {
            SmsManager smsManager = SmsManager.getDefault();

            //for(String number:  listNumbers){
                smsManager.sendTextMessage(listNumbers, null, msg, null, null);
            //}

            if(iSmsSending!=null)
                iSmsSending.onSmsSent();
        } catch (Exception e) {
            if(iSmsSending!=null)
                iSmsSending.onSmsFail();
            e.printStackTrace();
        }
    }
}
