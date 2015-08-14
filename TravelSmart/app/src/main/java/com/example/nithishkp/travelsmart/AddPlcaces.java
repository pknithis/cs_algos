package com.example.nithishkp.travelsmart;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;
import java.util.StringTokenizer;

public class AddPlcaces extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private String locations[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v("Nithish", "Add Places2");

        Intent it = getIntent();
//        Log.v("Nithish Loc",it.getStringExtra("LOCATIONS"));
        if(it!=null)
        {

            int i =0;

            locations=it.getStringArrayExtra("LOCATIONS");

            {
                //locations[i]=it.getStringExtra("LOCATIONS"+i);
              //  Log.v("Nithish Locs",locations[i]);
                //i++;

            }
        }
        setContentView(R.layout.activity_add_plcaces);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }
    public long distanceCalculate(LatLng l1, LatLng l2)
    {
        long dlong = (long)Math.abs(l1.longitude-l2.longitude);
        long dlat = (long)Math.abs(l1.latitude-l2.latitude);
        long a = (long)(Math.pow(Math.sin(dlat/2),2) + Math.cos(l1.latitude) * Math.cos(l2.latitude) * Math.pow(Math.sin(dlong/2),2));
        long c = (long)(2 *(Math.atan2(Math.sqrt(a),Math.sqrt(1-a))));
        return 6373 * c;

    }
    public Address searchLocation(String str)
    {
        Geocoder geocoder = new Geocoder(this);

    try {
         return geocoder.getFromLocationName(str, 1).get(0);
    }
    catch(Exception e)
    {

    }

        return null;
    }

    public void onMapReady(GoogleMap map) {
        // Add a marker in Sydney, Australia, and move the camera.
        System.out.print("Nithish onMapReady\n");
        mMap.setMyLocationEnabled(true);
        int i =0;
        LatLng previous = null;
        PolylineOptions pops = new PolylineOptions();
        while(i<locations.length)
        {
            Address address = searchLocation(locations[i]);

            LatLng sydney = new LatLng(address.getLatitude(), address.getLongitude());
            pops.add(sydney);

            Marker mr = map.addMarker(new MarkerOptions().position(sydney).title("Marker at " + locations[i]));

            if(previous!=null)
            {
                mr.setSnippet("Distance : "+distanceCalculate(previous,sydney)+" Km");
                Log.v("Nithish","Distance non zero");
            }
            else
            {
                mr.setSnippet("Distance : 0 Km");
                Log.v("Nithish", "Distance zero");
            }

            mr.showInfoWindow();
            map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            previous = sydney;
            i++;

        }
        Polyline pline = map.addPolyline(pops);
        pline.setColor(Color.RED);

    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
