package com.example.weatherapp;

import java.util.List;

/**
 * Created by Aina on 03.12.2018.
 */

class WeatherResponse {
    List<City> cities;
    int count;
    int code;
    String message;

    public WeatherResponse(List<City> cities, int count, int code, String message) {
        this.cities = cities;
        this.count = count;
        this.code = code;
        this.message = message;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
