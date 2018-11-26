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

public class RoomCodeActivity extends AppCompatActivity {
    private UserModel user;
    private RoomModel room;
    public static String firstName, lastName, role, userEmail, roomName, roomCode;
    Vector<UserModel> dbUsers = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_code);
        DBManager handler = new DBManager(this);

        Button mMapButton = (Button) findViewById( R.id.mapBtn );
        TextView mRoomCodeTextView = findViewById( R.id.room_code_text_view);
        TextView mRoomNameTextView = findViewById( R.id.room_name_text_view);
        Button mSettingsButton = (Button) findViewById( R.id.settingsBtn );
        ListView mRoomUsers = (ListView) findViewById(R.id.room_users);

        user = (UserModel) getIntent().getSerializableExtra("user");
        room = handler.retrieveRoom(user.getRoomCode());

        mRoomCodeTextView.setText("Room Code: " + room.getCode());

        firstName = user.getFirstName();
        lastName = user.getLastName();
        role = user.getRole();
        userEmail = user.getEmail();
        roomName = room.getName();
        roomCode = room.getCode();
        mRoomNameTextView.setText("Room Name: " + room.getName());

        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), MapActivity.class);
                startIntent.putExtra("user", user);
                startActivity(startIntent);
            }
        });

        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(startIntent);
            }
        });

        List<String> allUsers = new ArrayList<String>();

        dbUsers = handler.getUsersByRoom(user.getRoomCode());
        if(dbUsers != null) {
            for (UserModel user : dbUsers) {
                allUsers.add(user.getRole() + ": " + user.getFirstName() + " " + user.getLastName() + "\n");

            }

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                allUsers
        );

        mRoomUsers.setAdapter(adapter);

    }
}
