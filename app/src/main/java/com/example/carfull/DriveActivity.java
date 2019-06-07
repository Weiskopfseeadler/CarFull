package com.example.carfull;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class DriveActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private List<Location> locationList =new ArrayList<>();
    private AppInformation appInformation;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        appInformation = AppInformation.getInstance();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mapFragment.getMapAsync(this);
        getSystemService(Context.LOCATION_SERVICE);


        this.enableGPSUpdates();


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

    }


    public void onClick(View view) {

        disableeGPSUpdates();
        Intent intent = new Intent(this, EndOfDrive.class);
        startActivity(intent);

    }


    private boolean isLocationEnabled() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return false;

        }
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }





    @SuppressLint("MissingPermission")
    public void enableGPSUpdates() {
            locationManager.requestLocationUpdates("gps", 2, 5, locationListenerGPS);

    }

    public void disableeGPSUpdates() {
              locationManager.removeUpdates(locationListenerGPS);

    }


    private final LocationListener locationListenerGPS = new LocationListener() {

        public void onLocationChanged(Location location) {
            locationList.add(location);
            double longitudeGPS = location.getLongitude();
            double latitudeGPS = location.getLatitude();

            LatLng newMarker = new LatLng(latitudeGPS, longitudeGPS);
            mMap.addMarker(new MarkerOptions().position(newMarker).title(""));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(newMarker));
            float distancInMeters=0;
            if (locationList.size()>1) {

                 distancInMeters = locationList.get(locationList.size() -1).distanceTo(locationList.get(locationList.size()-2)) ;
            }
            appInformation.newExpenses.getDriveData().setKmDrived(distancInMeters);

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
////                    longitudeValueGPS.setText(longitudeGPS + "");
////                    latitudeValueGPS.setText(latitudeGPS + "");
////                    Toast.makeText(MainActivity.this, "GPS Provider update", Toast.LENGTH_SHORT).show();
//                }
//            });
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
    };

}
