package com.example.cse3311.loccareandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Vector<UserModel> dbUsers;
    private DBManager handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        handler = new DBManager(this);
        final UserModel user = (UserModel) getIntent().getSerializableExtra("user");
        RoomModel room = handler.retrieveRoom(user.getRoomCode());
        dbUsers = handler.getUsersByRoom(room.getCode());
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we
     * just add a marker near Africa.
     */
    @Override
    public void onMapReady(GoogleMap map) {

        for (UserModel currUser: dbUsers) {
            LatLng location = currUser.getCurrentLocation();
            currUser.setLatitude(location.latitude);
            currUser.setLongitude(location.longitude);
            handler.updateUserLocation(currUser);
            map.addMarker(new MarkerOptions().position(currUser.getCurrentLocation()).title(currUser.getFullName()));
        }
    }
}
