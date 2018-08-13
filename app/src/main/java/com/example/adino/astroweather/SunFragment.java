package com.example.adino.astroweather;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.text.DecimalFormat;

/**
 * Created by adino on 30.04.2018.
 */

public class SunFragment extends Fragment{
    private TextView sunriseTime;
    private TextView sunriseAzimuth;
    private TextView sunsetTime;
    private TextView sunsetAzimuth;
    private TextView civilMorningTwilightTime;
    private TextView civilEveningTwilightTime;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.sun, container, false);

        sunriseTime = (TextView) view.findViewById(R.id.sunriseTime);
        sunriseAzimuth = (TextView) view.findViewById(R.id.sunriseAzimuth);
        sunsetTime = (TextView) view.findViewById(R.id.sunsetTime);
        sunsetAzimuth = (TextView) view.findViewById(R.id.sunsetAzimuth);
        civilMorningTwilightTime = (TextView) view.findViewById(R.id.civilMorningTwilightTime);
        civilEveningTwilightTime = (TextView) view.findViewById(R.id.civilEveningTwilightTime);

        AstroCalculator.SunInfo astroSunInfo = AstroWeatherCalculator.getInstance().getAstroCalculator().getSunInfo();
        DecimalFormat f = new DecimalFormat("###.00");

        AstroDateTime sunrise = astroSunInfo.getSunrise();
        String time;
        if(sunrise!=null) {
            time = String.valueOf(sunrise.getHour()) + ":" + String.valueOf(sunrise.getMinute()) + ":" + String.valueOf(sunrise.getSecond());
        }
        else {
            time = "no value";
        }

        sunriseTime.setText(time);

        if(!Double.isNaN(astroSunInfo.getAzimuthRise())) {
            sunriseAzimuth.setText(String.valueOf(f.format(astroSunInfo.getAzimuthRise()) + "°"));
        }
        else {
            sunriseAzimuth.setText("no value");
        }

        AstroDateTime sunset = astroSunInfo.getSunset();
        if(sunset!=null){
            time = String.valueOf(sunset.getHour()) + ":" + String.valueOf(sunset.getMinute()) + ":" + String.valueOf(sunset.getSecond());
        }
        else {
            time = "no value";
        }

        sunsetTime.setText(time);

        if(!Double.isNaN(astroSunInfo.getAzimuthSet())){
            sunsetAzimuth.setText(String.valueOf(f.format(astroSunInfo.getAzimuthSet()) + "°"));
        } else
        {
            sunsetAzimuth.setText("no value");
        }



        AstroDateTime civilMorningTwilight = astroSunInfo.getTwilightMorning();

        time = String.valueOf(civilMorningTwilight.getHour()) + ":" + String.valueOf(civilMorningTwilight.getMinute()) + ":" + String.valueOf(civilMorningTwilight.getSecond());
        civilMorningTwilightTime.setText(time);

        AstroDateTime civilEveningTwilight = astroSunInfo.getTwilightEvening();

        time = String.valueOf(civilEveningTwilight.getHour()) + ":" + String.valueOf(civilEveningTwilight.getMinute()) + ":" + String.valueOf(civilEveningTwilight.getSecond());
        civilEveningTwilightTime.setText(time);




        return view;
    }


}
