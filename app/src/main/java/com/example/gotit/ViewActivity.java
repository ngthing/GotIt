package com.example.gotit;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    SharedPreferences preferences;
    TextView selectedBeginTime;
    TextView selectedEndTime;
    TextView message;
    TextView monday;
    TextView tuesday;
    TextView wednesday;
    TextView thursday;
    TextView friday;
    TextView saturday;
    TextView sunday;
    public static final String PREFS_NAME = "DEF";
    public static final String PREFS_KEY = "PREFSKEY_String";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        instantiateViews();
        setupViews();
        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        autoFill();
            /////////////
//            boolean monday_bool = preferences.getBoolean("monday",false);
//            if (monday_bool==true)
//            monday.setText("Monday");


    }

    private void instantiateViews() {
        monday = (TextView) findViewById(R.id.monday);
        tuesday = (TextView) findViewById(R.id.tuesday);
        wednesday = (TextView) findViewById(R.id.wednesday);
        thursday = (TextView) findViewById(R.id.thursday);
        friday = (TextView) findViewById(R.id.friday);
        saturday = (TextView) findViewById(R.id.saturday);
        sunday = (TextView) findViewById(R.id.sunday);

        message = (TextView) findViewById(R.id.message_tv);
        selectedBeginTime = (TextView) findViewById(R.id.begin_tv);
        selectedEndTime = (TextView) findViewById(R.id.end_tv);

    }

    private void autoFill() {
        if (preferences.getBoolean("monday", false)) {
            monday.setTextColor(Color.parseColor("#00FF00"));
        } else {
            monday.setTextColor(Color.parseColor("#FF0000"));
        }

    }
}
