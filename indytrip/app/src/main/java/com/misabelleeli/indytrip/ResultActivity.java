package com.misabelleeli.indytrip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.misabelleeli.indytrip.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultActivity extends Activity {
    private List<String> result_headers;
    private HashMap<String,List<Result>> result_items;
    private ExpandableListView result_list;
    private ExpandableListAdapter adapter;
    private TextView result_name_label;
    private TextView result_address_label;
    private ImageView result_image_view;

    private Button next;

    private void populateItems() {
        /*
        Result Object
        1st param - String image
        2nd param - String name
        3rd param - String addres
        4th param - String description
        5th param - int longtitidu
        6th param - int latitude
        */

        result_headers.add("Day 1");
        result_headers.add("Day 2");
        Result a = new Result("lucas","Lucas Oil Stadium","500 S Capitol Ave","awesome place",0,0);
        Result b = new Result("ncaa","NCAA Hall of Champions","White River State Park 801 W Washington St","awesome place2",0,0);
        Result c = new Result("children_museum","The Children’s Museum of Indianapolis",
                "3000 N Meridian St","awesome place3",0,0);
        Result d = new Result("art_museum","Indianapolis Museum of Art",
                "Indianapolis, IN 46208","awesome place4",0,0);

        ArrayList<Result> results1 = new ArrayList<Result>();
        ArrayList<Result> results2 = new ArrayList<Result>();
        results1.add(a);
        results1.add(b);
        results2.add(c);
        results2.add(d);
        result_items.put(result_headers.get(0), results1);
        result_items.put(result_headers.get(1), results2);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.in, R.anim.out);
        setContentView(R.layout.activity_result);


        result_list = (ExpandableListView) findViewById(R.id.result_list);
        result_headers = new ArrayList<String>() ;
        result_items = new HashMap<String, List<Result>>();
        populateItems();


        adapter = new ExpandableListAdapter(this, result_headers, result_items);
        result_list.setAdapter(adapter);



    }
    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> listHeaders; // header titles
        // child data in format of header title, child title
        private HashMap<String,List<Result>> listItems;
        private Result child;

        public ExpandableListAdapter(Context context, List<String> result_headers,
                                     HashMap<String, List<Result>> result_items) {

            this._context = context;
            this.listHeaders = result_headers;
            this.listItems = result_items;
        }

        @Override
        public Result getChild(int groupPosition, int childPosition) {
            return this.listItems.get(this.listHeaders.get(groupPosition))
                    .get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

//            final String childText = (String) getChild(groupPosition, childPosition);


            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.result_item, null);
            }
            result_image_view = (ImageView)convertView.findViewById(R.id.result_image);
            result_name_label = (TextView)convertView.findViewById(R.id.result_name_label);
            result_address_label = (TextView)convertView.findViewById(R.id.result_address_label);
            child = getChild(groupPosition, childPosition);

            Log.e("Name1", child.getResult_name());
            Log.e("Address1", child.getReseult_description());

            //Setting Items here
            //images - R.id.result_image
            //Name - R.id.result_name
            //Address - R.id.result_address
            result_name_label.setText(child.getResult_name());
            result_address_label.setText(child.getResult_address());

            Resources res = getResources();
            int resID = res.getIdentifier(child.getResullt_image(), "drawable", getPackageName());
            Drawable drawable = res.getDrawable(resID);
            result_image_view.setImageDrawable(drawable);

            //List view click listener
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Send data to server here
                    //hotel_name.getText().toString() <- hotel name
                    //hotel_address.getText().toString() <- hotel address
                    //add Longtitude and latitude variable to this intent

                    child = getChild(groupPosition, childPosition);
                    Log.e("Name2", child.getResult_name());
                    Log.e("Address2", child.getReseult_description());
                    //Intent i = new Intent(ResultActivity.this, GoogleMapActivity.class);
                    //startActivity(i);

                                    /*
                * "http://maps.google.com/maps?saddr="
                + Constants.latitude + ","
                + Constants.longitude + "&daddr="
                + latitude + "," + longitude

        */

                    double initLat = 0;
                    double initLon = 0;
                    double destLat = 0;
                    double destLon = 0;

                    if(child.getResult_name().equals("Lucas Oil Stadium"))
                    {
                        initLat = 39.766268;
                        initLon = -86.168557;
                        destLat =39.759862;
                        destLon = -86.164017;
                    }
                    else if(child.getResult_name().equals("NCAA Hall of Champions"))
                    {
                        destLat = 39.768041;
                        destLon = -86.171586;
                        initLat =39.759862;
                        initLon = -86.164017;

                    }
                    else if(child.getResult_name().equals("The Children’s Museum of Indianapolis"))
                    {
                        initLat = 39.766268;
                        initLon = -86.168557;
                        destLat =39.810473;
                        destLon = -86.158085;

                    }
                    else if(child.getResult_name().equals("Indianapolis Museum of Art"))
                    {
                        destLat = 39.826051;
                        destLon = -86.186052;
                        initLat =39.810473;
                        initLon = -86.158085;

                    }

                    String url = "http://maps.google.com/maps?saddr="+initLat+","+initLon
                            + "&daddr=" + destLat+ "," + destLon;
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent);

                }
            });
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this.listItems.get(this.listHeaders.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this.listHeaders.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this.listHeaders.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.result_header, null);
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.resultHeader);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.end_in, R.anim.end_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.result, menu);
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
