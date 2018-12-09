package com.example.weatherapp.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("/data/2.5/find")
//    Call<WeatherResponse> getData(@Query("lat") double lat, @Query("lon") double lon,
//                                  @Query("cnt") int cnt, @Query("appid") String appId);
    Single<WeatherResponse> getData(@Query("lat") double lat, @Query("lon") double lon,
                                    @Query("cnt") int cnt, @Query("appid") String appId);
}
