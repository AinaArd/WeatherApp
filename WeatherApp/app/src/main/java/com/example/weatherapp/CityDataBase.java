package com.example.weatherapp;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.weatherapp.entities.City;
import com.example.weatherapp.entities.Main;
import com.example.weatherapp.entities.Sys;
import com.example.weatherapp.entities.Wind;
import com.example.weatherapp.interfaces.WeatherDAO;

import static java.security.AccessController.getContext;

/**
 * Created by Aina on 08.12.2018.
 */

@Database(entities = {City.class, Wind.class, Sys.class, Main.class}, version = 1)
public abstract class CityDataBase extends RoomDatabase {
    private static CityDataBase database;
    public final static String DB_NAME = "my database";

    public abstract WeatherDAO getWeatherDao();

    public static CityDataBase getDatabase() {
        if (database == null) {
            database = Room.databaseBuilder(App.getContext(),
                    CityDataBase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
