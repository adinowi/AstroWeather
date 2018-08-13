package com.example.adino.astroweather;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by adino on 30.04.2018.
 */

public class SunMoonActivity extends AppCompatActivity {
    private TextView longitudeValue;
    private TextView latitudeValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.astro);

        longitudeValue = (TextView) findViewById(R.id.longitudeValue);
        latitudeValue = (TextView) findViewById(R.id.latitudeValue);


        longitudeValue.setText(String.valueOf(AstroWeatherCalculator.getInstance().getAstroCalculator().getLocation().getLongitude()));
        latitudeValue.setText(String.valueOf(AstroWeatherCalculator.getInstance().getAstroCalculator().getLocation().getLatitude()));


        if(getResources().getConfiguration().screenWidthDp < 600){
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
            viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
        }
        else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction  transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.sunFragment, new SunFragment());
            transaction.replace(R.id.moonFragment, new MoonFragment());
            transaction.commit();
        }

        AstroWeatherCalculator.getInstance().setActivity(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AstroWeatherCalculator.getInstance().setActivity(null);
    }

    @Override
    public void recreate() {
        super.recreate();
        Toast.makeText(SunMoonActivity.this,"Refreshed",Toast.LENGTH_SHORT).show();
    }
}
