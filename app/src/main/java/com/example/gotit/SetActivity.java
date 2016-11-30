package com.example.gotit;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Calendar;

public class SetActivity extends AppCompatActivity implements View.OnClickListener{
    Button submitButton;
    EditText message;
    CheckBox monday;
    CheckBox tuesday;
    CheckBox wednesday;
    CheckBox thursday;
    CheckBox friday;
    CheckBox saturday;
    CheckBox sunday;
    String  message_from_edit_text;
    String start;
    String end;
    Button  btnTimePicker, btnEndTimePicker;
    EditText txtTime, txtEndTime;
    private int mHour, mMinute, mHourEnd,mMinuteEnd;

    public static final String PREFS_NAME = "DEF";
    public static final String PREFS_KEY = "PREFSKEY_String";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        setupViews();

        //get permissions
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {android.Manifest.permission.SEND_SMS, android.Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS};

        if(!getPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }

    private void setupViews() {
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtTime=(EditText)findViewById(R.id.in_time);
        btnTimePicker.setOnClickListener(this);
        btnEndTimePicker=(Button)findViewById(R.id.btn_timeEnd);
        txtEndTime=(EditText)findViewById(R.id.in_timeEnd);
        btnEndTimePicker.setOnClickListener(this);
        txtTime.setFocusable(false);
        txtEndTime.setFocusable(false);

    }

    //validayion
    private boolean validation(){
        message_from_edit_text =message.getText().toString();
        if (message_from_edit_text.isEmpty() || start.isEmpty() || end.isEmpty()){
            showDialog();
            return false;
        }

        if (monday.isChecked() || tuesday.isChecked() || wednesday.isChecked() || thursday.isChecked()|| friday.isChecked()||saturday.isChecked()||sunday.isChecked()){
            return true;
        }

        return false;
    }

    @Override
    public void onClick(View v) {

        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(android.widget.TimePicker view, int hourOfDay,
                                              int minute) {
                            String minute_str = "";
                            String hour_str = "";
                            if (minute < 10) {
                                minute_str = "0" + minute;
                            } else {
                                minute_str = minute+"";
                            }
                            if (hourOfDay < 10) {
                                hour_str = "0" + hourOfDay;
                            } else {
                                hour_str = "" + hourOfDay;
                            }
                            txtTime.setText(hour_str + ":" + minute_str);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnEndTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHourEnd = c.get(Calendar.HOUR_OF_DAY);
            mMinuteEnd = c.get(Calendar.MINUTE);
            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(android.widget.TimePicker view, int hourOfDay,
                                              int minute) {
                            String minute_str = "";
                            String hour_str = "";
                            if (minute < 10) {
                                minute_str = "0" + minute;
                            } else {
                                minute_str = minute+"";
                            }
                            if (hourOfDay < 10) {
                                hour_str = "0" + hourOfDay;
                            } else {
                                hour_str = "" + hourOfDay;
                            }
                            txtEndTime.setText(hour_str + ":" + minute_str);
                        }
                    }, mHourEnd, mMinuteEnd, false);
            timePickerDialog.show();

        }

        if (v == submitButton) {
            if (validation()) {
                SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                String begin = txtTime.getText().toString();
                String end = txtEndTime.getText().toString();
                String message = txtEndTime.getText().toString();

                preferences.edit().putString("begin", begin).apply();
                preferences.edit().putString("end", end).apply();
                preferences.edit().putString("message", message).apply();

                preferences.edit().putBoolean("active", true).apply();

                if (monday.isChecked()) {
                    preferences.edit().putBoolean("monday", true).apply();
                } else {
                    preferences.edit().putBoolean("monday", false).apply();
                }

                if (tuesday.isChecked()) {
                    preferences.edit().putBoolean("tuesday", true).apply();
                } else {
                    preferences.edit().putBoolean("tuesday", false).apply();
                }

                if (wednesday.isChecked()) {
                    preferences.edit().putBoolean("wednesday", true).apply();
                } else {
                    preferences.edit().putBoolean("wednesday", false).apply();
                }

                if (thursday.isChecked()) {
                    preferences.edit().putBoolean("thursday", true).apply();
                } else {
                    preferences.edit().putBoolean("thursday", false).apply();
                }

                if (friday.isChecked()) {
                    preferences.edit().putBoolean("friday", true).apply();
                } else {
                    preferences.edit().putBoolean("friday", false).apply();
                }

                if (saturday.isChecked()) {
                    preferences.edit().putBoolean("saturday", true).apply();
                } else {
                    preferences.edit().putBoolean("saturday", false).apply();
                }

                if (sunday.isChecked()) {
                    preferences.edit().putBoolean("sunday", true).apply();
                } else {
                    preferences.edit().putBoolean("sunday", false).apply();
                }


                Intent intent = new Intent(this, ViewActivity.class);
                this.startActivity(intent);
            }
        }
    }

    //show the message error
    public void showDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(SetActivity.this).create();
        alertDialog.setTitle("Missing field");
        alertDialog.setMessage("Please enter all fields");
        // Alert dialog button
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Alert dialog action goes here
                        // onClick button code here
                        dialog.dismiss();// use dismiss to cancel alert dialog
                    }
                });
        alertDialog.show();
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