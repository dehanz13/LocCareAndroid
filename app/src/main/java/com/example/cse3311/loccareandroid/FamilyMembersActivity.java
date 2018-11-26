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

public class FamilyMembersActivity extends AppCompatActivity {
    private UserModel user;
    private RoomModel room;
    Vector<UserModel> dbUsers = new Vector<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);
        DBManager handler = new DBManager(this);

        TextView mRoomCodeTextView = findViewById( R.id.room_code_text_view);
        TextView mRoomNameTextView = findViewById( R.id.room_name_text_view);
        ListView mRoomUsers = (ListView) findViewById(R.id.room_users);
        user = (UserModel) getIntent().getSerializableExtra("user");
        room = handler.retrieveRoom(user.getRoomCode());

        mRoomCodeTextView.setText("Room Code: " + room.getCode());
        mRoomNameTextView.setText("Room Name: " + room.getName());

        Button backButton = (Button) findViewById( R.id.backButton );
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(startIntent);
            }
        });

        List<String> allUsers = new ArrayList<String>();

        dbUsers = handler.getUsersByRoom(user.getRoomCode());
        if(dbUsers != null) {
            for (UserModel user : dbUsers) {
                allUsers.add(user.getFirstName() + " " + user.getLastName() + "\n");

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

