package com.example.cse3311.loccareandroid;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private RoomModel room;
    public static String roomCode;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        RoomModel roomM = new RoomModel();
        RoomCodeActivity RCA = new RoomCodeActivity();

        roomCode = RCA.roomCode;

        Button profileButton = (Button) findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), ProfileActivity.class);
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
        Button familyMembersButton = (Button) findViewById( R.id.familyMembersButton );
        familyMembersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent startIntent = new Intent(getApplicationContext(), FamilyMembersActivity.class);
                //startActivity(startIntent);
            }
        });

        Button logOutBtn = (Button) findViewById( R.id.logOutBtn );
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(startIntent);
            }
        });

        Button inviteMemberBtn = (Button) findViewById(R.id.inviteMemberBtn);
        inviteMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent startIntent = new Intent(getApplicationContext(), LoginActivity.class);
                //startActivity(startIntent);

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Room Code Copied!",roomCode);
                clipboard.setPrimaryClip(clip);
                showAlertDialogButtonClicked(roomCode);

            }
        });
    }
    public void showAlertDialogButtonClicked(String roomCodex) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your Room Code:");
        builder.setMessage(roomCodex);

        // add a button
        builder.setPositiveButton("COPY", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();



    }
}
