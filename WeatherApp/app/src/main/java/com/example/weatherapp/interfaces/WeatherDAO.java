package com.example.weatherapp.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.weatherapp.entities.City;

import java.util.List;

import io.reactivex.Observable;

/*
  Created by Aina on 09.12.2018.
 */
@Dao
public interface WeatherDAO {
    @Query("SELECT * FROM weather")
    Observable<List<City>> getAllCities();

    @Insert()
    void insertAll(List<City> cities);
}
