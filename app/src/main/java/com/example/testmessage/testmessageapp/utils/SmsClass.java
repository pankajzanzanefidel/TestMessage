package com.example.testmessage.testmessageapp.utils;

import android.content.Context;
import android.telephony.SmsManager;

public class SmsClass {

    public interface ISmsSending {
        void onSmsSent();

        void onSmsFail();
    }

    private ISmsSending iSmsSending = null;

     public SmsClass(ISmsSending iSmsSending) {
        this.iSmsSending = iSmsSending;
    }

    public void sendMsg(Context context, String msg, String number) {

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, msg, null, null);
            iSmsSending.onSmsSent();
        } catch (Exception e) {
            iSmsSending.onSmsFail();
            e.printStackTrace();
        }
    }
}
