package com.example.weatherapp;

/**
 * Created by Aina on 03.12.2018.
 */

public class Weather {
    private String country;
    private String city;
    private String temperature;

    public Weather(String country, String city, String temperature) {
        this.country = country;
        this.city = city;
        this.temperature = temperature;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
