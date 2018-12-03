package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailedActivity extends AppCompatActivity {

    TextView temp;
    TextView humidity;
    TextView pressure;
    TextView wind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        temp = findViewById(R.id.tv_temp);
        humidity = findViewById(R.id.tv_humidity);
        pressure = findViewById(R.id.tv_pressure);
        wind = findViewById(R.id.tv_wind);

        Intent intent = getIntent();
        temp.setText(intent.getStringExtra("temp"));
        humidity.setText(intent.getStringExtra("humidity"));
        pressure.setText(intent.getStringExtra("pressure"));
        wind.setText(intent.getStringExtra("wind"));
    }
}
