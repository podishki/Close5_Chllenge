package com.example.kiran.close5_chllenge;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.kiran.close5_chllenge.model.Districts;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<Districts> districtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();
        //Log.d("KIRAN", "latitude: "+ intent.getStringExtra("LATITUDE") );
        //Log.d("KIRAN", "latitude: "+ intent.getStringExtra("LONGITUDE") );
        Double latitude = Double.parseDouble(intent.getStringExtra("LATITUDE"));
        Double longitude = Double.parseDouble(intent.getStringExtra("LONGITUDE"));
        String address = intent.getStringExtra("ADDRESS");
        String category = intent.getStringExtra("CATEGORY");

        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(latitude, longitude);
        //LatLng sydney = new LatLng(37.7837848211584, -122.398932711095);
        if(category.equals("ASSAULT") || category.equals("BURGLARY") || category.equals("VANDALISM") ){
            mMap.addMarker(new MarkerOptions().position(location).title("Category: "+ category).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }else if(category.equals("NON-CRIMINAL") || category.equals("LARCENY/THEFT")){
            mMap.addMarker(new MarkerOptions().position(location).title("Category: "+ category).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        }else{
            mMap.addMarker(new MarkerOptions().position(location).title("Category: "+ category).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        }
        mMap.getMaxZoomLevel();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(location, 12.0f);
        mMap.animateCamera(camera);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

    }
}
