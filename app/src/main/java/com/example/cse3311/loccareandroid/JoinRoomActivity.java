package com.example.cse3311.loccareandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class JoinRoomActivity extends AppCompatActivity {
    private UserModel user;
    private String roomCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        user = (UserModel) getIntent().getSerializableExtra("user");

        Button mContinueButton = (Button) findViewById( R.id.continueBtn );
        final EditText mRoomCodeInput = findViewById( R.id.room_code_edit_text );

        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomCode = mRoomCodeInput.getText().toString();

                if (!joinRoom(roomCode)) {
                    // error for invalid code
                    Toast.makeText(JoinRoomActivity.this, roomCode + " is invalid",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Intent startIntent = new Intent(getApplicationContext(), RoomCodeActivity.class);
                startIntent.putExtra("user", user);
                startActivity(startIntent);
            }
        });
    }

    private boolean joinRoom(String userCode) {
        // this function will help the user go to a room
        DBManager db = new DBManager(JoinRoomActivity.this);

        if (!db.roomExists(userCode)) {
            return false;
        }

        user.setRoomCode(userCode);
        db.updateUserRoomCode(user);
        return true;
    }
}
