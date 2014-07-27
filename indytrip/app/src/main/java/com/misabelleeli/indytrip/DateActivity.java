package com.misabelleeli.indytrip;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.misabelleeli.indytrip.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateActivity extends Activity  {
    private Button startButton;
    private Button checkInButton;
    private Button checkOutButton;
    private TextView numberOfDays;

    private int pYear;
    private int pMonth;
    private int pDay;
    private int checkInYear = 0;
    private int checkInMonth = 0;
    private int checkInDay = 0;
    private int diffDays = 0;

    private DatePickerDialog.OnDateSetListener pCheckInListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            pYear = year;
            pMonth = monthOfYear;
            pDay = dayOfMonth;
            checkInYear = pYear;
            checkInMonth = pMonth;
            checkInDay = pDay;
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
        Date checkInDate = null;
        Date checkOutDate = null;
        checkInButton.setText(
                new StringBuilder()
                .append(pMonth + 1).append("/")
                .append(pDay).append("/")
                .append(pYear)
        );
        if(!checkOutButton.getText().toString().equals("Choose Date")) {
            SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
            try {
                checkInDate = date.parse(checkInButton.getText().toString());
                checkOutDate = date.parse(checkOutButton.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            diffDays = (int) ((checkOutDate.getTime() - checkInDate.getTime())/ (1000 * 60 * 60 * 24));
            //Same day counts as 1 day
            diffDays++;
            numberOfDays.setText(diffDays+" Days");

        }
    }
    private void updateCheckOutDate() {
        Date checkInDate = null;
        Date checkOutDate = null;
        checkOutButton.setText(
                new StringBuilder()
                        .append(pMonth + 1).append("/")
                        .append(pDay).append("/")
                        .append(pYear)
        );
        if(!checkInButton.getText().toString().equals("Choose Date")) {
            SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
            try {
                checkInDate = date.parse(checkInButton.getText().toString());
                checkOutDate = date.parse(checkOutButton.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            diffDays = (int) ((checkOutDate.getTime() - checkInDate.getTime())/ (1000 * 60 * 60 * 24));
            //Same day counts as 1 day
            diffDays++;
            numberOfDays.setText(diffDays+" Days");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        startButton = (Button)findViewById(R.id.startButton);
        checkInButton = (Button)findViewById(R.id.checkInButton);
        checkOutButton = (Button)findViewById(R.id.checkOutButton);
        numberOfDays = (TextView)findViewById(R.id.numberOfDays);

        //Get current date
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(diffDays < 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DateActivity.this);
                    builder.setMessage("Please choose proper date of travel.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do nothing
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                    return;
                }

                //send data to server
                //diffDays


                Intent i = new Intent(DateActivity.this, HotelActivity.class);
                startActivity(i);
            }
        });

        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);

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
                if(checkInYear != 0) {
                    return new DatePickerDialog(this, pCheckOutListener, checkInYear, checkInMonth, checkInDay);
                }
                else {
                    return new DatePickerDialog(this, pCheckOutListener, pYear, pMonth, pDay);
                }

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
