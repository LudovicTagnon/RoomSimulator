package com.example.roomsimulator;

import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCreation = (Button) findViewById(R.id.buttonCreation);
        buttonCreation.setOnClickListener((v) -> {
            Intent ic = new Intent(MainActivity.this, CreationActivity.class);
            startActivity(ic);
        });

        Button buttonNavigation = (Button) findViewById(R.id.buttonNavigation);
        buttonNavigation.setOnClickListener((v) -> {
            Intent ic = new Intent(MainActivity.this, NavigationActivity.class);
            startActivity(ic);
        });


    }
}