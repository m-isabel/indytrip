package com.misabelleeli.indytrip;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by M.Isabel on 7/27/2014.
 */
public class PlaceholderFragment extends MapFragment implements LocationListener {

    private GoogleMap mMap = getMap();
    private LocationManager lManager;
    private LayoutInflater Minflater;
    private double []latitudes = {39.76737,39.77224,39.77643,39.77475,39.77418,39.76885,
            39.78179,39.77964,39.77564,39.76595,39.76702,39.76720,39.76832,
            39.77338,39.77669,39.75241,39.75740,39.75893,39.76423,39.76593,
            39.76866,39.76722,39.77803,39.76481,39.77383};
    private double []longtitudes={-86.16474,-86.15260,-86.14727,-86.16984,-86.16348,
            -86.15736,-86.16590, -86.14212,-86.15217,-86.16689,-86.16016,-86.15832,
            -86.17027,
            -86.17543,-86.16119,-86.13995,-86.14549,-86.14700,-86.16161,
            -86.16216,-86.15284,-86.15416,-86.15631,-86.15650,-86.15043};
    private String []title = {"Indiana Government Center","Mass Ave. and Alabama",
            "Mass Ave. and Park","Michicgan and Blackford","Michigan and Senate",
            "Monument Circle","North End of Canal","North End of Mass Ave.",
            "North and Alabama","Victory Field","Washington and Illinois",
            "Washington and Meridian","White River State Park", "IUPUI Campus Center",
            "Glick Peace Walk","Fountain Square","Fletcher Place -Virginia and Norwood",
            "Fletcher Place -Virginia and Merrill","Convention Center at Georgia Street",
            "Convention Center - Maryland and Capitol", "City Market",
            "City County Building","Central Library", "Bankers Life Fieldhouse",
            "Athenaeum"};
    private double currentLat;
    private double currentLong;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMap = getMap();
        mMap.setMyLocationEnabled(true);
        lManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                400, 1000, this);

        //mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        for(int i = 0; i < title.length;i++)
        {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitudes[i], longtitudes[i])).snippet("description")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)))
                    .setTitle(title[i]);
        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //This will redirect it to GoogleMaps
                String url = "http://maps.google.com/maps?daddr="+currentLat+","+currentLong;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        mMap.animateCamera(cameraUpdate);
        lManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        @Override
        public View getInfoWindow(Marker marker) {
            // Getting view from the layout file info_window_layout
            View v = Minflater.inflate(R.layout.custom_infowindow, null);

            //TODO: get correct titles and descriptions.

            // Getting the position from the marker
            LatLng latLng = marker.getPosition();
            String name = marker.getTitle();
            // Getting reference to the TextView to set latitude
            TextView title = (TextView) v.findViewById(R.id.title);

            // Getting reference to the TextView to set longitude
            TextView description = (TextView) v.findViewById(R.id.snippet);

            currentLat = latLng.latitude;
            currentLong = latLng.longitude;

            title.setText(name);

            description.setText("Click for Directions.");

            // Returning the view containing InfoWindow contents
            return v;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }

    /*
    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_google_map, container, false);

        return rootView;
    }
    */
}
