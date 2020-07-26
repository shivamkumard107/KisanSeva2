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

        cityField.setText("Dwarka, New Delhi, India");
        updatedField.setText("4-Feb-2020 9:00:05 AM");
        detailsField.setText("Sunny");
        currentTemperatureField.setText("34 °C");
        humidity_field.setText("Humidity: " + "62%");
        pressure_field.setText("Pressure: " + "1010 hPa");
        weatherIcon.setImageResource(R.drawable.sun);
    }
}