package com.example.cse3311.loccareandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateRoomActivity extends AppCompatActivity {
    private UserModel user;
    private RoomModel room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        user = (UserModel) getIntent().getSerializableExtra("user");

        Button mContinueButton = findViewById(R.id.create_room_continue_button);
        final EditText mAddRoomNameText = findViewById(R.id.create_room_add_room_name);

        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roomName = mAddRoomNameText.getText().toString();
                RoomModel room = createRoom(roomName);
                DBManager dbManager = new DBManager(CreateRoomActivity.this);

                dbManager.addNewRoom(room);
                user.setRoomCode(room.getCode());

                Log.d("create room on click", "set roomCode made user code " + user.getRoomCode());

                user = dbManager.updateUserRoomCode(user);

                Log.d("create room on click", room.getName());

                Intent startIntent = new Intent(getApplicationContext(), RoomCodeActivity.class);
                startIntent.putExtra("user", user);
                startActivity(startIntent);
            }
        });
    }

    private RoomModel createRoom(String RoomName) {
        RoomGenerator roomGenerator = new RoomGenerator();
        String roomCode = roomGenerator.generate();

        room = new RoomModel();
        room.setCode(roomCode);
        room.setName(RoomName);

        return room;
    }
}
