package com.example.cse3311.loccareandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class instructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);


        TextView minstructionsMsg = findViewById( R.id.instructionsTextView);

        minstructionsMsg.setText(
                "\n" +
                "This app was developed by 3 students from UT Arlington for a project. \n" +
                "LocCare was initially developed to help parents keep track of their children's locations at any time.\n" +
                "The mission was to keep this app as simple as possible and free. In the future, we might consider finishing\n" +
                "this app and have it on the Google Play Store. \n" +
                "Developers: Kyrell Dixon, Mohammed Al Aadhami, and Danniel Hansel. \n" +
                "Android Emulator: Nexus 5X, API 26");



        Button backButton = (Button) findViewById( R.id.backButton );
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(startIntent);
            }
        });

    }
}
