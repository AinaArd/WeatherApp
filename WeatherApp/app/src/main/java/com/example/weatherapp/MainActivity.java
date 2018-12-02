package com.example.weatherapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MyCallback {
    private static WeatherApi weatherApi;
    private CityAdapter cityAdapter;
    Data data;
    private Retrofit retrofit;

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
        data = new Data();
        final Collection<City> cities = data.getCities();
        final RecyclerView usersRecycleView = findViewById(R.id.recycleView);
        usersRecycleView.setLayoutManager(new LinearLayoutManager(this));
        cityAdapter = new CityAdapter((List<City>) cities);
        usersRecycleView.setAdapter(cityAdapter);

        cityAdapter.setCities(cities);

        App.getApi().getData("Kazan", 55.78874, 49.12214, "Russia", "-11 deg")
                .enqueue(new Callback<List<City>>() {
                    @Override
                    public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                        cities.addAll(response.body());
                        usersRecycleView.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<City>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    @Override
    public void callback(String info) {
        Intent intent = new Intent(this, DetailedActivity.class);
        intent.putExtra("info", info);
        startActivity(intent);
    }

   /* private void initRecyclerView() {
        RecyclerView usersRecycleView = findViewById(R.id.recycleView);
        usersRecycleView.setLayoutManager(new LinearLayoutManager(this));
        cityAdapter = new CityAdapter();
        usersRecycleView.setAdapter(cityAdapter);
    }

    private void loadCities() {
        data = new Data();
        Collection<City> cities = data.getCities();
        cityAdapter.setCities(cities);
    }*/

    public boolean getPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    public static WeatherApi getApi() {
        return weatherApi;
    }

}
