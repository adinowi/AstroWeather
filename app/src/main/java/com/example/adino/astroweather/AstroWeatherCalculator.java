package com.example.adino.astroweather;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by adino on 30.04.2018.
 */

public class AstroWeatherCalculator {
    private AstroCalculator astroCalculator;
    private Integer refreshTime;
    private Handler refrashHandler;
    private SunMoonActivity activity;

    public AstroCalculator getAstroCalculator() {
        return astroCalculator;
    }

    public void setRefreshTime(Integer refreshTime) {
        refrashHandler.removeCallbacksAndMessages(null);
        this.refreshTime = refreshTime;
        autoRefresh();
    }

    public Integer getRefreshTime() {
        return refreshTime;
    }

    public void setActivity(SunMoonActivity activity) {
        this.activity = activity;
    }

    public void autoRefresh(){
        refrashHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Calendar calendar = Calendar.getInstance();
                AstroDateTime astroDateTime = new AstroDateTime(calendar.get(Calendar.YEAR)+1900,calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND),calendar.get(Calendar.ZONE_OFFSET)/3600000,true);
                astroCalculator.setDateTime(astroDateTime);
                if(activity!=null){
                    activity.recreate();
                }
                autoRefresh();
            }
        }, refreshTime * 60000);
    }

    private AstroWeatherCalculator(){

        Calendar calendar = Calendar.getInstance();


        AstroDateTime astroDateTime = new AstroDateTime(calendar.get(Calendar.YEAR)+1900,calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), calendar.get(Calendar.ZONE_OFFSET)/3600000,true);
        astroCalculator = new AstroCalculator(astroDateTime, new AstroCalculator.Location(0,0));
        refreshTime = 1;
        refrashHandler = new Handler();
        autoRefresh();
    }

    private static AstroWeatherCalculator calcualtor = new AstroWeatherCalculator();
    public static AstroWeatherCalculator getInstance(){
        return calcualtor;
    }

}
