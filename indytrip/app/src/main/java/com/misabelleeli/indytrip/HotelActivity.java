package com.misabelleeli.indytrip;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.misabelleeli.indytrip.R;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends Activity {
    private List<Hotel> hotels = new ArrayList<Hotel>();
    private List<String> searchList = new ArrayList<String>();
    private ListView hotelList;
    private TextView defaultView;
    private TextView defaultLabel;
    private ArrayAdapter<Hotel> adapter;
    private EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        if(hotels.size() == 0)
        {
            populateHotels();
        }
        hotelList = (ListView)findViewById(R.id.hotelsList);
        defaultView = (TextView)findViewById(R.id.defaultBackgroundView);
        searchBox = (EditText)findViewById(R.id.search_view);
        defaultLabel = (TextView)findViewById(R.id.defaultLabel);


        generateList(hotelList);

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                adapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        defaultLabel.setText(Html.fromHtml(getResources().getString(R.string.default_label)));
        defaultLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HotelActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });


    }

    private void populateHotels() {
        hotels.add(new Hotel("The Alexender", "333 South Delaware Street, Indianapolis"));
        hotels.add(new Hotel("The Westin","50 South Capitol Ave., Indianapolis"));
        hotels.add(new Hotel("Hilton Garden Inn Downtown", "10 East Market Street, Indianapolis"));
        hotels.add(new Hotel("Hampton Inn Downtown"," 105 S. Meridian St., Indianapolis"));
        hotels.add(new Hotel("JW Marriott", "10 S West Street, 701 W. Washington Street, Indianapolis"));
        hotels.add(new Hotel("Sheraton City Centre Hotel", "31 West Ohio Street, Indianapolis"));
        for(Hotel hotel : hotels) {
            searchList.add(hotel.getAddress());
        }
    }
    private void generateList(ListView view) {
        defaultView.setVisibility(View.GONE);
        adapter = new MyListAdapter(getBaseContext(), R.layout.hotel_item, hotels);
        view.setAdapter(adapter);
        view.setTextFilterEnabled(true);
    }
    private class MyListAdapter extends ArrayAdapter<Hotel> implements Filterable {
        List<Hotel> list;
        List<Hotel> original_list;
        private HotelFilter filter;
        private int resource;
        public MyListAdapter(Context context, int resourceId, List<Hotel> list) {
            super(context, resourceId, list);
            this.list = list;
            this.original_list = new ArrayList<Hotel>(list);
            this.resource = resourceId;
        }

        @Override
        public Filter getFilter() {
            if (filter == null) {
                filter = new HotelFilter();
            }
            return filter;
        }

        @Override
        public int getCount() { return list.size(); }

        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if(rowView == null) {
                LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(resource, parent, false);
            }
            Hotel curHotel = list.get(position);
            String name = curHotel.getName();
            String address = curHotel.getAddress();
            ImageView hotel_image = (ImageView)rowView.findViewById(R.id.hotel_image);
            TextView hotel_name = (TextView)rowView.findViewById(R.id.hotel_name);
            TextView hotel_address = (TextView)rowView.findViewById(R.id.hotel_address);
            //Set hotel image
            Resources res = getResources();
            String image_name = curHotel.getName().toLowerCase().replaceAll("\\s+", "_");
            int resID = res.getIdentifier(image_name, "drawable", getPackageName());
            Drawable drawable = res.getDrawable(resID);
            hotel_image.setImageDrawable(drawable);

            hotel_name.setText(name);

            hotel_address.setText(address);


            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(HotelActivity.this, ProfileActivity.class);
                    startActivity(i);
                }
            });

            return rowView;
        }
        private class HotelFilter extends Filter {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                charSequence = charSequence.toString().toLowerCase();
                if(charSequence == null || charSequence.length() == 0) {
                    results.values = original_list;
                    results.count = original_list.size();
                }
                else {
                    List<Hotel> result = new ArrayList<Hotel>();
                    for(Hotel hotel : original_list) {
                        if(hotel.getName().toLowerCase().contains(charSequence)) {
                            result.add(hotel);
                        }
                    }
                    results.values = result;
                    results.count = result.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if(filterResults.count == 0) {
                    list = original_list;
                    notifyDataSetInvalidated();
                    defaultView.setVisibility(View.VISIBLE);
                    hotelList.setVisibility(View.GONE);
                }
                else {
                    defaultView.setVisibility(View.GONE);
                    hotelList.setVisibility(View.VISIBLE);
                    list = (List<Hotel>) filterResults.values;
                    notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hotel, menu);
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
