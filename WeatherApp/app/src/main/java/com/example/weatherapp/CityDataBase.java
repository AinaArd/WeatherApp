package com.example.weatherapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.weatherapp.entities.City;
import com.example.weatherapp.entities.Main;
import com.example.weatherapp.entities.Sys;
import com.example.weatherapp.entities.Wind;
import com.example.weatherapp.interfaces.WeatherDAO;

/**
 * Created by Aina on 08.12.2018.
 */

@Database(entities = {City.class, Wind.class, Sys.class, Main.class}, version = 1)
abstract class CityDataBase extends RoomDatabase {
    public abstract WeatherDAO getWeatherDao();
}
