package com.example.weatherapp.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.weatherapp.entities.City;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface WeatherDAO {
    @Query("SELECT * FROM weather")
    Single<List<City>> getAllCities();

    @Insert
    void insertAll(List<City> cities);

    @Delete
    void delete(City city);
}
