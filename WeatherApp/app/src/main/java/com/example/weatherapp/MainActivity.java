package com.example.weatherapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static double KAZANLAT = 55.8;
    private final static double KAZANLON = 49.0;
    private final static int NEARESTCITIESAMOUNT = 20;
    private final static String apiKey = "56fc6c6cb76c0864b4cd055080568268";

    private CityAdapter cityAdapter;
    private List<City> cities;
    private RecyclerView recyclerView;
    private MyCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*if (getPermission()) {
            initRecyclerView();
            loadCities();

        } else {
            System.out.println("no such permission");
        }*/
        cities = new ArrayList<>();
        callback = new MyCallback() {
            @Override
            public void callback(City city) {
                Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                intent.putExtra("temp", city.getMain().getTemp());
                intent.putExtra("humidity", city.getMain().getHumidity());
                intent.putExtra("pressure", city.getMain().getPressure());
                intent.putExtra("wind", city.getWind().getDeg());
                startActivity(intent);
            }
        };

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cityAdapter = new CityAdapter(new ArrayList<City>(0), callback);
        recyclerView.setAdapter(cityAdapter);

        NetworkService.getNetworkService().getWeatherApi().getData(KAZANLAT, KAZANLON, NEARESTCITIESAMOUNT, apiKey)
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                        WeatherResponse weatherResponse = response.body();
                        if (weatherResponse != null) {
                            List<City> cities = weatherResponse.getCities();
                            cityAdapter.setCities(cities);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                        Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public boolean getPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }
}
