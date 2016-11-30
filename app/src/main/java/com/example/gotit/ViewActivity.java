package com.example.gotit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener {
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
    Button button;
    public static final String PREFS_NAME = "DEF";
    public static final String PREFS_KEY = "PREFSKEY_String";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        instantiateViews();
        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        autoFill();
//            boolean monday_bool = preferences.getBoolean("monday",false);
//            if (monday_bool==true)
//            monday.setText("Monday");

   }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SetActivity.class);
        this.startActivity(intent);
    }

    private void instantiateViews() {
        monday = (TextView) findViewById(R.id.monday);
        tuesday = (TextView) findViewById(R.id.tuesday);
        wednesday = (TextView) findViewById(R.id.wednesday);
        thursday = (TextView) findViewById(R.id.thursday);
        friday = (TextView) findViewById(R.id.friday);
        saturday = (TextView) findViewById(R.id.saturday);
        sunday = (TextView) findViewById(R.id.sunday);
        button = (Button) findViewById(R.id.button);
        message = (TextView) findViewById(R.id.message_tv);
        selectedBeginTime = (TextView) findViewById(R.id.begin_tv);
        selectedEndTime = (TextView) findViewById(R.id.end_tv);
        button.setOnClickListener(this);
    }

    private void autoFill() {
        if (preferences.getBoolean("monday", false)) {
            monday.setTextColor(Color.parseColor("#00FF00"));
        } else {
            monday.setTextColor(Color.parseColor("#FF0000"));
        }
        if (preferences.getBoolean("tuesday", false)) {
            tuesday.setTextColor(Color.parseColor("#00FF00"));
        } else {
            tuesday.setTextColor(Color.parseColor("#FF0000"));
        }
        if (preferences.getBoolean("wednesday", false)) {
            wednesday.setTextColor(Color.parseColor("#00FF00"));
        } else {
            wednesday.setTextColor(Color.parseColor("#FF0000"));
        }
        if (preferences.getBoolean("thursday", false)) {
            thursday.setTextColor(Color.parseColor("#00FF00"));
        } else {
            thursday.setTextColor(Color.parseColor("#FF0000"));
        }
        if (preferences.getBoolean("friday", false)) {
            friday.setTextColor(Color.parseColor("#00FF00"));
        } else {
            friday.setTextColor(Color.parseColor("#FF0000"));
        }
        if (preferences.getBoolean("saturday", false)) {
            saturday.setTextColor(Color.parseColor("#00FF00"));
        } else {
            saturday.setTextColor(Color.parseColor("#FF0000"));
        }
        if (preferences.getBoolean("sunday", false)) {
            sunday.setTextColor(Color.parseColor("#00FF00"));
        } else {
            sunday.setTextColor(Color.parseColor("#FF0000"));
        }
        // Set the message space to the preconfigured message
        message.setText(preferences.getString("message",""));
        // Set the begin time
        selectedBeginTime.setText(preferences.getString("begin",""));
        // Set the end time
        selectedEndTime.setText(preferences.getString("end",""));

        //blah
    }
}
