package com.example.weatherapp;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Aina on 02.12.2018.
 */

class Data {
    Collection<City> getCities() {
        return Arrays.asList(new City("1", "2"),
                new City("1", "2"),
                new City("1", "2"),
                new City("1", "2"),
                new City("1", "2"),
                new City("1", "2"),
                new City("1", "2"),
                new City("1", "2"),
                new City("1", "2"),
                new City("1", "2"),
                new City("1", "2"))
        ;
    }
}

