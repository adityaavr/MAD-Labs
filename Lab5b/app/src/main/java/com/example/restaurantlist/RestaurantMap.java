package com.example.restaurantlist;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.restaurantlist.databinding.ActivityRestaurantMapBinding;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class RestaurantMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double lat;
    private double lon;
    private String restaurantName;
    private GPSTracker gpsTracker;
    private LatLng RESTAURANT;
    private LatLng ME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_map);

        lat = getIntent().getDoubleExtra("LATITUDE", 0);
        lon = getIntent().getDoubleExtra("LONGITUDE", 0);
        restaurantName = getIntent().getStringExtra("NAME");

        // Initialize GPSTracker to get current location
        gpsTracker = new GPSTracker(this);
        double myLat = gpsTracker.getLatitude();
        double myLon = gpsTracker.getLongitude();
        ME = new LatLng(myLat, myLon);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.restaurant_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        RESTAURANT = new LatLng(lat, lon);
        Marker restaurant = mMap.addMarker(new MarkerOptions().position(RESTAURANT).title(restaurantName));
        Marker me = mMap.addMarker(new MarkerOptions().position(ME).title("ME")
                .snippet("My Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_me)));

        // Center the camera to the restaurant location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RESTAURANT, 15));
    }
}
