package com.example.chemicalrule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

public class Map extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap map;
    FusedLocationProviderClient client;
    SupportMapFragment mapFragment;
    Button btnSave;
    double latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        client = LocationServices.getFusedLocationProviderClient(this);
        if(ActivityCompat.checkSelfPermission(Map.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
        }else{
            ActivityCompat.requestPermissions(Map.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
        btnSave = (Button) findViewById(R.id.btnSelectLocation);
        btnSave.setOnClickListener(this::selectedLocation);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setTrafficEnabled(true);
        map.setOnMapClickListener((latLng -> {
            map.clear();
            Log.d("arg0",latLng.latitude+"||||"+latLng.longitude);
            MarkerOptions options = new MarkerOptions().position(latLng)
                    .title("Estas aqui");
            map.addMarker(options);
            longitude = latLng.longitude;
            latitude = latLng.latitude;

        }));
    }
    public void selectedLocation(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("latitude",latitude);
        resultIntent.putExtra("longitude", longitude);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation(){
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener((l)->{
            if(l != null){
                mapFragment.getMapAsync((g)->{
                    LatLng latLng = new LatLng(l.getLatitude(),
                            l.getLongitude());
                    MarkerOptions options = new MarkerOptions().position(latLng)
                            .title("Estas aqui");
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                    longitude = l.getLongitude();
                    latitude = l.getLatitude();
                    map.addMarker(options);

                });
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }
    }
}