package com.vlgo.vlgo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vlgo.vlgo.R;
import com.vlgo.vlgo.component.ComponentDostoprims;
import com.vlgo.vlgo.component.Entity;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private int type, index;
    private Entity currentEntity;

    private void initEntity(){
        switch (type){
            case 0:
                currentEntity = ComponentDostoprims.getInstance().getDostoprims().get(index);
                break;
            case 1:
                currentEntity = ComponentDostoprims.getInstance().getPlyazhi().get(index);
                break;
            case 2:
                currentEntity = ComponentDostoprims.getInstance().getExcursions().get(index);
                break;
            case 3:
                currentEntity = ComponentDostoprims.getInstance().getExtremal().get(index);
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        type = getIntent().getIntExtra("type", 0);
        index = getIntent().getIntExtra("index", 0);
        initEntity();

        setTitle(currentEntity.getName());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        requestPerms();
    }

    //Сразу запрашиваем несколько разрешений
    private void requestPerms() {
        String[] perm = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, perm, 123);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_back:
                finish();
                break;
        }
        return  true;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(android.location.Location location) {

            }
        });

        // Add a marker in Sydney and move the camera
        LatLng position = new LatLng(currentEntity.getCoordX(), currentEntity.getCoordY());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(position);
        markerOptions.title(currentEntity.getName());

        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));

    }
}