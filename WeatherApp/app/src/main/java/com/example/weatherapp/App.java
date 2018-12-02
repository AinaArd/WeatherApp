package com.example.weatherapp;

import android.app.Application;

import retrofit2.Retrofit;

/**
 * Created by Aina on 02.12.2018.
 */

public class App extends Application {

    private static WeatherApi weatherApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApi = retrofit.create(WeatherApi.class);
    }

    public static WeatherApi getApi() {
        return weatherApi;
    }
}