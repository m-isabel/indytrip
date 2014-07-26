package com.misabelleeli.indytrip;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;

import com.misabelleeli.indytrip.R;

import java.util.Calendar;

public class DateActivity extends FragmentActivity  {
    private Button startButton;
    private Button checkInButton;
    private Button checkOutButton;

    private int pYear;
    private int pMonth;
    private int pDay;
    private com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener checkInListener = new com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(com.fourmob.datetimepicker.date.DatePickerDialog datePickerDialog, int i, int i2, int i3) {
            pYear = i;
            pMonth = i2;
            pDay = i3;
            updateCheckInDate();
        }
    };
    private com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener checkOutListener = new com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(com.fourmob.datetimepicker.date.DatePickerDialog datePickerDialog, int i, int i2, int i3) {
            pYear = i;
            pMonth = i2;
            pDay = i3;
            updateCheckOutDate();
        }
    };
    private DatePickerDialog.OnDateSetListener pCheckInListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            pYear = year;
            pMonth = monthOfYear;
            pDay = dayOfMonth;
            updateCheckInDate();

        }
    };
    private DatePickerDialog.OnDateSetListener pCheckOutListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            pYear = year;
            pMonth = monthOfYear;
            pDay = dayOfMonth;
            updateCheckOutDate();

        }
    };
    private void updateCheckInDate() {
        checkInButton.setText(
                new StringBuilder()
                .append(pMonth + 1).append("/")
                .append(pDay).append("/")
                .append(pYear)
        );
    }
    private void updateCheckOutDate() {
        checkOutButton.setText(
                new StringBuilder()
                        .append(pMonth + 1).append("/")
                        .append(pDay).append("/")
                        .append(pYear)
        );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        startButton = (Button)findViewById(R.id.startButton);
        checkInButton = (Button)findViewById(R.id.checkInButton);
        checkOutButton = (Button)findViewById(R.id.checkOutButton);

        //Get current date
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);

        final com.fourmob.datetimepicker.date.DatePickerDialog checkInDialog = com.fourmob.datetimepicker.date.DatePickerDialog.newInstance(checkInListener, pYear, pMonth, pDay, false);
        final com.fourmob.datetimepicker.date.DatePickerDialog checkOutDialog = com.fourmob.datetimepicker.date.DatePickerDialog.newInstance(checkOutListener, pYear, pMonth, pDay, false);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DateActivity.this, HotelActivity.class);
                startActivity(i);
            }
        });

        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialog(0);
                checkInDialog.setVibrate(false);
                checkInDialog.setYearRange(pYear,pYear+1);
                checkInDialog.show(getSupportFragmentManager(),"datepicker");


            }
        });
        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case 0:
                return new DatePickerDialog(this, pCheckInListener, pYear, pMonth, pDay);
            case 1:
                return new DatePickerDialog(this, pCheckOutListener, pYear, pMonth, pDay);

        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.date, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
