package com.example.gotit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


/*

Sherry: Splash screen if statement
In the OnCreate method in the MainActivity Class, can you set up an if statement
that checks our preferences to see if an autoresponse has been set. If so, launch
ViewActivity. Otherwise, launch SetActivity. Keep in mind that while we should always
have either 'true' or 'false' saved in the preferences, When the app is launched for the first time,
 nothing will be set so make sure if nothing is set it will go to SetActivity.
*
* */
public class MainActivity extends AppCompatActivity {
    Boolean isactive;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        //get permissions
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {android.Manifest.permission.SEND_SMS, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.ACCESS_FINE_LOCATION};

        if(!getPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        Log.d("!!!!!!!!!!","!!!!!!!!!!");

        //validation
        isactive =  preferences.getBoolean("active ",false);
        if(isactive){
          Intent intent = new Intent(this, ViewActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, SetActivity.class);
            startActivity(intent);
        }
    }

    public boolean getPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


}
