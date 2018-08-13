package com.example.adino.astroweather;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

/**
 * Created by adino on 28.04.2018.
 */

public class SettingsActivity extends AppCompatActivity {
    private Spinner refreshTimeSpinner;
    private Button btnApply;
    private EditText latitudeInput;
    private EditText longitudeInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);




        refreshTimeSpinner = (Spinner) findViewById(R.id.refreshTime);
        Integer[] timeValues = {1,5,10,15,30,60};
        refreshTimeSpinner.setAdapter(new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, timeValues));



        latitudeInput = (EditText) findViewById(R.id.latitudeInput);
        longitudeInput = (EditText) findViewById(R.id.longitudeInput);

        if(savedInstanceState != null){
            if(savedInstanceState.get("latitudeInput")!=null){
                latitudeInput.setText(savedInstanceState.get("latitudeInput").toString());
            }
            if(savedInstanceState.get("longitudeInput")!=null){
                latitudeInput.setText(savedInstanceState.get("longitudeInput").toString());
            }
            if(savedInstanceState.get("refreshTime")!=null){
                refreshTimeSpinner.setSelection((Integer) savedInstanceState.get("refreshTime"));
            }
        }

        btnApply = (Button) findViewById(R.id.apply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String longitudeInputText = longitudeInput.getText().toString();
                StringBuilder s = new StringBuilder();
                if(longitudeInputText.length() > 0){
                    Double longitudeValue = Double.valueOf(longitudeInputText);
                    if(longitudeValue > 180 || longitudeValue < -180){
                        s.append("Wrong longitude value\n");
                    }
                    else {
                        AstroWeatherCalculator.getInstance().getAstroCalculator().getLocation().setLongitude(longitudeValue);
                        s.append("longitude is OK\n");
                    }
                }

                String latitudeInputText = latitudeInput.getText().toString();
                if(latitudeInputText.length() > 0){
                    Double latitudeValue = Double.valueOf(latitudeInputText);
                    if(latitudeValue > 90 || latitudeValue < -90){
                        s.append("Wrong latitude value\n");
                    }
                    else {
                        AstroWeatherCalculator.getInstance().getAstroCalculator().getLocation().setLatitude(latitudeValue);
                        s.append("latitude is OK\n");
                    }
                }

                AstroWeatherCalculator.getInstance().setRefreshTime(Integer.valueOf(refreshTimeSpinner.getSelectedItem().toString()));
                s.append("refresh time is OK\n");

                Toast.makeText(SettingsActivity.this, s.toString(),Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("latitudeInput",latitudeInput.toString());
        outState.putString("longitudeInput",longitudeInput.toString());

        outState.putInt("refreshTime",refreshTimeSpinner.getSelectedItemPosition());

    }
}
