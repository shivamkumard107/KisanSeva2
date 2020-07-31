package com.uttarakhand.kisanseva2.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.uttarakhand.kisanseva2.R;

public class WeatherActivity extends AppCompatActivity {
    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, updatedField;
    ImageView weatherIcon;
    Typeface weatherFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        cityField = (TextView) findViewById(R.id.city_field);
        updatedField = (TextView) findViewById(R.id.updated_field);
        detailsField = (TextView) findViewById(R.id.details_field);
        currentTemperatureField = (TextView) findViewById(R.id.current_temperature_field);
        humidity_field = (TextView) findViewById(R.id.humidity_field);
        pressure_field = (TextView) findViewById(R.id.pressure_field);
        weatherIcon = findViewById(R.id.weather_icon);

        cityField.setText(R.string.weather_city);
        updatedField.setText(R.string.weather_time);
        detailsField.setText(R.string.weather_overall);
        currentTemperatureField.setText(R.string.weather_temp);
        humidity_field.setText(getString(R.string.weather_humidity));
        pressure_field.setText(getString(R.string.weather_pressure));
        weatherIcon.setImageResource(R.drawable.sun);
    }
}