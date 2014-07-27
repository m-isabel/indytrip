package com.misabelleeli.indytrip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    private SeekBar nightBar = null;
    private LinkedHashMap hashMapPref = new LinkedHashMap();
    private TextView budget_status;
    private TextView art_status;
    private TextView culture_status;
    private TextView history_status;
    private TextView sports_status;
    private TextView outdoors_status;
    private TextView food_status;
    private TextView night_status;

    ArrayList<String> budget_status_list;
    ArrayList<String> art_status_list;
    ArrayList<String> culture_status_list;
    ArrayList<String> history_status_list;
    ArrayList<String> sports_status_list;
    ArrayList<String> outdoors_status_list;
    ArrayList<String> food_status_list;
    ArrayList<String> night_status_list;

    //This is what you will be saving to DB
    private int progressChanged = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.in, R.anim.out);
        setContentView(R.layout.activity_preference);
        populateStatus();
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Send data to Server
                //hashMapPref <- key: category name, value: rating number (0 ~ 4)

                
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
        nightBar = (SeekBar) findViewById(R.id.night_bar);

        budget_status = (TextView)findViewById(R.id.budget_status);
        art_status = (TextView)findViewById(R.id.art_status);
        culture_status = (TextView)findViewById(R.id.culture_status);
        history_status = (TextView)findViewById(R.id.history_status);
        sports_status = (TextView)findViewById(R.id.sports_status);
        outdoors_status = (TextView)findViewById(R.id.outdoors_status);
        food_status = (TextView)findViewById(R.id.food_status);
        night_status = (TextView)findViewById(R.id.night_status);

        budget_status.setText(budget_status_list.get(0));
        art_status.setText(art_status_list.get(0));
        history_status.setText(history_status_list.get(0));
        outdoors_status.setText(outdoors_status_list.get(0));
        sports_status.setText(sports_status_list.get(0));
        food_status.setText(food_status_list.get(0));
        night_status.setText(night_status_list.get(0));
        culture_status.setText(culture_status_list.get(0));


        seekBarControl.setOnSeekBarChangeListener(this);
        artBar.setOnSeekBarChangeListener(this);
        cultureBar.setOnSeekBarChangeListener(this);
        historyBar.setOnSeekBarChangeListener(this);
        sportsBar.setOnSeekBarChangeListener(this);
        outdoorBar.setOnSeekBarChangeListener(this);
        foodBar.setOnSeekBarChangeListener(this);
        nightBar.setOnSeekBarChangeListener(this);
    }
    public void populateStatus() {
        budget_status_list = new ArrayList<String>(
                Arrays.asList("Homeless", "Lean", "Moderate", "Indulgent", "Luxury")
        );
        art_status_list = new ArrayList<String>(
                Arrays.asList("Not A Priority", "Limited Interest", "Art's pretty Cool", "Important", "Art Lover")
        );
        culture_status_list = new ArrayList<String>(
                Arrays.asList("Not A Priority", "Less is More", "When In Rome..", "Cultural Connoisseur", "Complete Immersion")
        );
        history_status_list = new ArrayList<String>(
                Arrays.asList("Not a Priority", "Limited Interest", "History's Cool", "Important", "History Buff")
        );
        sports_status_list = new ArrayList<String>(
                Arrays.asList("Balls Are Scary", "Limited Interest", "Like Sports", "Let's Play", "Live For Sports!")
        );
        outdoors_status_list = new ArrayList<String>(
                Arrays.asList("Mostly Urban Trip", "Limited Interst", "Like The Outdoors", "Need Fresh Air", "Into The Wild")
        );
        food_status_list = new ArrayList<String>(
                Arrays.asList("Where's McDonald", "Food Is Food", "Like To Eat", "Important", "Major Foodie")
        );
        night_status_list = new ArrayList<String>(
                Arrays.asList("Not a Priority", "Limited Interest", "Clubbing is Cool", "Important", "Night Owl")
        );
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
                hashMapPref.put("budget",temp);
                budget_status.setText(budget_status_list.get(temp));
                break;
            case R.id.art_bar:
                hashMapPref.put("art",temp);
                art_status.setText(art_status_list.get(temp));
                break;
            case R.id.history_bar:
                hashMapPref.put("history",temp);
                history_status.setText(history_status_list.get(temp));
                break;
            case R.id.outdoors_bar:
                hashMapPref.put("outdoors",temp);
                outdoors_status.setText(outdoors_status_list.get(temp));
                break;
            case R.id.sports_bar:
                hashMapPref.put("sports",temp);
                sports_status.setText(sports_status_list.get(temp));
                break;
            case R.id.food_bar:
                hashMapPref.put("food",temp);
                food_status.setText(food_status_list.get(temp));
                break;
            case R.id.night_bar:
                hashMapPref.put("nightLife",temp);
                night_status.setText(night_status_list.get(temp));
                break;
            case R.id.culture_bar:
                hashMapPref.put("culture",temp);
                culture_status.setText(culture_status_list.get(temp));
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
            return 0;
        }
        else if(i >= 20 && i < 40)
        {
            return 1;
        }
        else if(i >= 40 && i < 60)
        {
            return 2;
        }
        else if(i >= 60 && i < 80)
        {
            return 3;
        }
        else{
            return 4;
        }
    }
}
