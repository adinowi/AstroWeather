package com.example.adino.astroweather;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;



/**
 * Created by adino on 01.05.2018.
 */

public class CustomPagerAdapter extends FragmentPagerAdapter {
    static private int number = 2;

    public CustomPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SunFragment();
            case 1:
                return new MoonFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return number;
    }


}
