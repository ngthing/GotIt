package com.example.gotit;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Cody on 11/1/2016.
 */

public class Methods {
    public static String getDay(int day) {
        switch(day){
            case 0:
                return "sunday";
            case 1:
                return "monday";
            case 2:
                return "tuesday";
            case 3:
                return "wednesday";
            case 4:
                return "thursday";
            case 5:
                return  "friday";
            case 6:
                return "saturday";
            default:
                return null;
        }
    }

    public static String getTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
        String date = sdf.format(c.getTime());
        Log.d("Method.getTime()",date);
        return date;
    }

    // return 1 when t1 is later, -1 when t1 is earlier, and 0 when equal
    public static int compareTimes(String t1, String t2) {
        try {
            String[] data1 = t1.split(":");
            String[] data2 = t2.split(":");
            int hour1 = Integer.parseInt(data1[0]);
            int hour2 = Integer.parseInt(data2[0]);
            int min1 = Integer.parseInt(data1[1]);
            int min2 = Integer.parseInt(data2[1]);
            if (hour1 < hour2) {
                return -1;
            } else if (hour1 > hour2) {
                return 1;
            } else {
                if (min1 < min2) {
                    return -1;
                } else if (min1 > min2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (Exception e) {
            Log.e("Methods.compareTimes()", e.getMessage());
            return 0;
        }
    }

    public static void sendAutoResponse(Context c, String phone){
        SmsManager smsManager = SmsManager.getDefault();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);


        String message = preferences.getString("message", "No message");

        smsManager.sendTextMessage(phone, null, message, null, null);
    }

}