package com.example.gotit;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.util.Log;


import java.util.Calendar;


/**
 * Created by Cody on 11/1/2016.
 */


public class SmsReceiver extends BroadcastReceiver {
    private String TAG = SmsReceiver.class.getSimpleName();


    public SmsReceiver() {
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the data (SMS data) bound to intent


        if (validate(context)) {
            Bundle bundle = intent.getExtras();


            SmsMessage[] msgs = null;

            String str = "";

            if (bundle != null) {
                // Retrieve the SMS Messages received
                Object[] pdus = (Object[]) bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];

                String phone = "";
                // For every SMS message received
                for (int i=0; i < msgs.length; i++) {
                    // Convert Object array
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    // Sender's phone number
                    phone = msgs[i].getOriginatingAddress();
                }

                Log.e("!!!!!!!!!", "TEST");

                //if (validate(context)) {
                if (true) {
                    Methods.sendAutoResponse(context, phone);
                }
            }
        }
    }

    private Boolean validate(Context context) {
        Calendar c = Calendar.getInstance();
        String day = Methods.getDay(c.get(Calendar.DAY_OF_WEEK));
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        if (preferences.getBoolean("active", false)) {
            if (preferences.getString(day,"").equals("true")) {
                String currTime = Methods.getTime();
                String beginTime = preferences.getString("begin", "");
                String endTime = preferences.getString("end", "");

                // if currTime is later than beginTime and before endTime
                if (Methods.compareTimes(currTime, beginTime) >= 0 && Methods.compareTimes(currTime, endTime) <= 0) {
                    return true;
                }
            }
        }

        return false;
    }

}


