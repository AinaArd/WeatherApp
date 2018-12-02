package com.example.weatherapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Aina on 02.12.2018.
 */

public interface WeatherApi {
    @GET("/api/get")
    Call<List<City>> getData(@Query("name") String cityName, @Query("lat") double lat, @Query("lon") double lon,
                             @Query("country") String countryName, @Query("temp") String cityTemp);
}
