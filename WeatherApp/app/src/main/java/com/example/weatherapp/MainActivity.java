package com.example.weatherapp;

import android.Manifest;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.weatherapp.entities.City;
import com.example.weatherapp.interfaces.MyCallback;
import com.example.weatherapp.network.NetworkService;
import com.example.weatherapp.network.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    private final static double KAZANLAT = 55.7887;
    private final static double KAZANLON = 49.1221;
    private final static int NEAREST_CITIES_AMOUNT = 20;
    private final static String apiKey = "56fc6c6cb76c0864b4cd055080568268";
    public final static String DB_NAME = "my database";

    private CityAdapter cityAdapter;
    private List<City> cities;
    private RecyclerView recyclerView;
    private MyCallback callback;
    private CityDataBase database;


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
        callback = city -> {
            Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
            intent.putExtra("temp", city.getMain().getTemp());
            intent.putExtra("humidity", city.getMain().getHumidity());
            intent.putExtra("pressure", city.getMain().getPressure());
            intent.putExtra("wind", city.getWind().getDeg());
            startActivity(intent);
        };

        database = Room.databaseBuilder(this,
                CityDataBase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cityAdapter = new CityAdapter(new ArrayList<City>(0), callback);
        recyclerView.setAdapter(cityAdapter);

       /* NetworkService.getNetworkService().getWeatherApi().getData(KAZANLAT, KAZANLON, NEAREST_CITIES_AMOUNT, apiKey)
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                        WeatherResponse weatherResponse = response.body();
                        if (weatherResponse != null) {
                            List<City> cities = weatherResponse.getCities();
                            Observable<List<City>> citiesFlow = Observable.fromArray(cities);
                            Observable<List<City>> dbCities = database.getWeatherDao().getAllCities();

                            Observable.concat(citiesFlow, dbCities)
                                    .filter(new Predicate<List<City>>() {
                                        @Override
                                        public boolean test(List<City> cities) throws Exception {
                                            return !cities.isEmpty();
                                        }
                                    })
                                    .first(Collections.<City>emptyList())
                                    .subscribe();
                            cityAdapter.setCities(cities);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                        Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                    }
                });*/

        NetworkService.getNetworkService()
                .getWeatherApi()
                .getData(KAZANLAT, KAZANLON, NEAREST_CITIES_AMOUNT, apiKey)
                .map(WeatherResponse::getCities)
                .map(list -> {
                    database.getWeatherDao().insertAll(list);
                    return list;
                })
                .onErrorResumeNext(error ->
                        database.getWeatherDao().getAllCities()
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    cityAdapter.setCities(list);
                });
    }

    public boolean getPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }
}
