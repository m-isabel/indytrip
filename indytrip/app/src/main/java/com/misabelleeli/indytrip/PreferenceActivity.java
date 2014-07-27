package com.misabelleeli.indytrip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.LinkedHashMap;

public class PreferenceActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    Button next;
    private SeekBar seekBarControl = null;
    private SeekBar artBar = null;
    private SeekBar cultureBar = null;
    private SeekBar historyBar = null;
    private SeekBar sportsBar = null;
    private SeekBar outdoorBar = null;
    private SeekBar foodBar = null;
    private SeekBar musicBar = null;
    private SeekBar nightLifeBar = null;
    private LinkedHashMap hashMapPref = new LinkedHashMap();

    //This is what you will be saving to DB
    private int progressChanged = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.in, R.anim.out);
        setContentView(R.layout.activity_preference);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PreferenceActivity.this, ResultActivity.class);
                startActivity(i);
            }
        });

        seekBarControl = (SeekBar) findViewById(R.id.budget_bar);
        artBar = (SeekBar) findViewById(R.id.art_bar);
        cultureBar = (SeekBar) findViewById(R.id.culture_bar);
        historyBar = (SeekBar) findViewById(R.id.history_bar);
        sportsBar = (SeekBar) findViewById(R.id.sports_bar);
        outdoorBar = (SeekBar) findViewById(R.id.outdoors_bar);
        foodBar = (SeekBar) findViewById(R.id.food_bar);
        musicBar = (SeekBar) findViewById(R.id.music_bar);
        nightLifeBar = (SeekBar) findViewById(R.id.nightLife_bar);

        seekBarControl.setOnSeekBarChangeListener(this);
        artBar.setOnSeekBarChangeListener(this);
        cultureBar.setOnSeekBarChangeListener(this);
        historyBar.setOnSeekBarChangeListener(this);
        sportsBar.setOnSeekBarChangeListener(this);
        outdoorBar.setOnSeekBarChangeListener(this);
        foodBar.setOnSeekBarChangeListener(this);
        musicBar.setOnSeekBarChangeListener(this);
        nightLifeBar.setOnSeekBarChangeListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.preference, menu);

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

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        int temp = getCorrectValue(i);
        progressChanged = i;

        switch(seekBar.getId())
        {
            case R.id.budget_bar:
                hashMapPref.put("budget",i);
                break;
            case R.id.art_bar:
                hashMapPref.put("art",temp);
                break;
            case R.id.history_bar:
                hashMapPref.put("history",temp);
                break;
            case R.id.outdoors_bar:
                hashMapPref.put("outdoors",temp);
                break;
            case R.id.sports_bar:
                hashMapPref.put("sports",temp);
                break;
            case R.id.food_bar:
                hashMapPref.put("food",temp);
                break;
            case R.id.music_bar:
                hashMapPref.put("music",temp);
                break;
            case R.id.nightLife_bar:
                hashMapPref.put("nightLife",temp);
                break;
            case R.id.culture_bar:
                hashMapPref.put("culture",temp);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        /*Toast.makeText(PreferenceActivity.this,"seek bar progress:"+progressChanged,
                Toast.LENGTH_SHORT).show();
        */
    }

    private int getCorrectValue(int i)
    {
        if(i >= 0 && i < 20)
        {
            return 1;
        }
        else if(i >= 20 && i < 40)
        {
            return 2;
        }
        else if(i >= 40 && i < 60)
        {
            return 3;
        }
        else if(i >= 60 && i < 80)
        {
            return 4;
        }
        else{
            return 5;
        }
    }
}
