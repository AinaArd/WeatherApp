package com.example.weatherapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aina on 03.12.2018.
 */

public class NetworkService {
    private static NetworkService service;
    private static final String BASE_URL = "https://openweathermap.org";
    private Retrofit retrofit;

    public static NetworkService getNetworkService() {
        if (service == null) {
            service = new NetworkService();
        }
        return service;
    }

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public WeatherApi getWeatherApi() {
        return retrofit.create(WeatherApi.class);
    }
}
