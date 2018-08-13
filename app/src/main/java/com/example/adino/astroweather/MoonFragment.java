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

public class MoonFragment extends Fragment {
    private TextView moonriseTime;
    private TextView moonsetTime;
    private TextView fullMoonDate;
    private TextView newMoonDate;
    private TextView moonIllumination;
    private TextView moonAge;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.moon, container, false);

        moonriseTime = (TextView) view.findViewById(R.id.moonriseTime);
        moonsetTime = (TextView) view.findViewById(R.id.moonsetTime);
        fullMoonDate = (TextView) view.findViewById(R.id.fullmoonDate);
        newMoonDate = (TextView) view.findViewById(R.id.newMoonDate);
        moonIllumination = (TextView) view.findViewById(R.id.moonIlluminationValue);
        moonAge = (TextView) view.findViewById(R.id.moonAgeValue);

        AstroCalculator.MoonInfo astroMoonInfo = AstroWeatherCalculator.getInstance().getAstroCalculator().getMoonInfo();

        DecimalFormat f = new DecimalFormat("##0.00");

        AstroDateTime moonrise = astroMoonInfo.getMoonrise();
        if(moonrise!=null){
            moonriseTime.setText(moonrise.getHour() + ":" + moonrise.getMinute() + ":" + moonrise.getSecond());
        }
        else {
            moonriseTime.setText("no value");

        }

        AstroDateTime moonset = astroMoonInfo.getMoonset();
        if(moonset!=null){
            moonsetTime.setText(moonset.getHour() + ":" + moonset.getMinute() + ":" + moonset.getSecond());
        }
        else {
            moonsetTime.setText("no value");
        }


        AstroDateTime fullmoon = astroMoonInfo.getNextFullMoon();
        fullMoonDate.setText(fullmoon.getDay() + "." + fullmoon.getMonth() + "." + fullmoon.getYear());

        AstroDateTime newmoon = astroMoonInfo.getNextNewMoon();
        newMoonDate.setText(newmoon.getDay() + "." + newmoon.getMonth() + "." + newmoon.getYear());

        moonIllumination.setText(f.format(astroMoonInfo.getIllumination()));

        moonAge.setText(String.valueOf(f.format(astroMoonInfo.getAge())));



        return view;
    }
}
