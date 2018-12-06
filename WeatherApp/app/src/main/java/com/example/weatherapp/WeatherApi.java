package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("/data/2.5/find")
    Call<WeatherResponse> getData(@Query("lat") double lat, @Query("lon") double lon,
                                  @Query("cnt") int cnt, @Query("appid") String appId);
}
