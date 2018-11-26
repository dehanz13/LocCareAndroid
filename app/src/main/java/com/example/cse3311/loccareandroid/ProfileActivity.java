package com.example.cse3311.loccareandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProfileActivity extends AppCompatActivity {
    private UserModel user;
    private RoomModel room;
    public static String firstName, lastName, role, userEmail, roomName;
    Vector<UserModel> dbUsers = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        RoomModel roomM = new RoomModel();
        RoomCodeActivity RCA = new RoomCodeActivity();
        DBManager handler = new DBManager(this);
        TextView mnameTextView = findViewById( R.id.nameTextView);
        TextView mroleTextView = findViewById( R.id.roleTextView);
        TextView memailTextView = findViewById( R.id.emailTextView);
        TextView mroomTextView = findViewById( R.id.roomTextView);

        firstName = RCA.firstName;
        lastName = RCA.lastName;
        role = RCA.role;
        userEmail = RCA.userEmail;
        roomName = RCA.roomName;


        //user = (UserModel) getIntent().getSerializableExtra("user");
        //room = handler.retrieveRoom(user.getRoomCode());

        //mroomTextView.setText("Room Name: " + room.getName());
        System.out.println("-----------------------------------ROOM NAME:" + roomName);
        mnameTextView.setText("Name: " + firstName + " " + lastName);
        mroleTextView.setText("Role: " + role);
        memailTextView.setText("Email: " + userEmail);
        mroomTextView.setText("Room: " + roomName);

        Button backBtn = (Button) findViewById( R.id.backBtn );
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), SettingsActivity.class);

                startActivity(startIntent);
            }
        });

        Button mMapButton = (Button) findViewById( R.id.mapBtn );
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(startIntent);
            }
        });


        //------//

    }
}
