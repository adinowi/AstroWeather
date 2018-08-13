package com.example.adino.astroweather;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.astrocalculator.AstroDateTime;

import java.time.Clock;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnSettings;
    private ImageButton btnSunMoon;
    private TextView longitudeValue;
    private TextView latitudeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSettings = (ImageButton) findViewById(R.id.settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        btnSunMoon = (ImageButton) findViewById(R.id.sunMoon);
        btnSunMoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SunMoonActivity.class));
            }
        });



        longitudeValue = (TextView) findViewById(R.id.longitudeValue);
        latitudeValue = (TextView) findViewById(R.id.latitudeValue);


        longitudeValue.setText(String.valueOf(AstroWeatherCalculator.getInstance().getAstroCalculator().getLocation().getLongitude()));
        latitudeValue.setText(String.valueOf(AstroWeatherCalculator.getInstance().getAstroCalculator().getLocation().getLatitude()));

        AstroWeatherCalculator.getInstance();

    }


    @Override
    protected void onResume() {
        super.onResume();

        longitudeValue.setText(String.valueOf(AstroWeatherCalculator.getInstance().getAstroCalculator().getLocation().getLongitude()));
        latitudeValue.setText(String.valueOf(AstroWeatherCalculator.getInstance().getAstroCalculator().getLocation().getLatitude()));

    }
}
