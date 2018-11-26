package com.example.cse3311.loccareandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class CreateOrJoinRoomActivity extends AppCompatActivity {
    private UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_join_room);

        user = (UserModel) getIntent().getSerializableExtra("user");
        Log.d("user from create-or-join",user.getFirstName());

        Button mJoinRoomParentButton = (Button) findViewById( R.id.joinRoomParentBtn );
        Button mCreateRoomButton = findViewById( R.id.createRoomBtn );

        mJoinRoomParentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), JoinRoomActivity.class);
                startIntent.putExtra("user", user);
                startActivity(startIntent);
            }
        });

        mCreateRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), CreateRoomActivity.class);
                startIntent.putExtra("user", user);
                startActivity(startIntent);
            }
        });
    }
}
