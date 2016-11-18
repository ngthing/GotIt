package com.example.gotit;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class SetActivity extends AppCompatActivity implements View.OnClickListener{
    Button submitButton;
    EditText begin_et;
    EditText end_et;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        setupViews();
    }

    private void setupViews() {
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

        begin_et = (EditText) findViewById(R.id.begin_et);
        end_et = (EditText) findViewById(R.id.end_et);

        begin_et.setOnClickListener(this);
        end_et.setOnClickListener(this);

        begin_et.setFocusable(false);
    }

    //validayion
    private boolean validation(){
        message_from_edit_text =message.getText().toString();
        start = begin_et.getText().toString();
        end = end_et.getText().toString();

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
        switch(v.getId()) {
            case R.id.submitButton:
                //do whatever
                break;
            case R.id.begin_et:
                TimePicker mTimePicker = new TimePicker();
                mTimePicker.show(getFragmentManager(), "Select time");
                break;
            case R.id.end_et:
                TimePicker mTimePicker2 = new TimePicker();
                mTimePicker2.show(getFragmentManager(), "Select time");
                break;

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

    public static class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }
        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {

            Toast.makeText(getActivity(),"Selected Time: " + String.valueOf(hourOfDay) + " : " + String.valueOf(minute), Toast.LENGTH_LONG);
        }
    }

}