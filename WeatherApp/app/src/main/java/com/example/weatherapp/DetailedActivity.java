package com.example.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailedActivity extends AppCompatActivity implements MyCallback {

    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        description = findViewById(R.id.tv_description);
        Bundle b = getIntent().getExtras();
        String info = (String) b.get("info");
        description.setText(info);
    }

    @Override
    public void callback(String info) {
        description.setText(info);
    }
}
