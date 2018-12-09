package com.example.weatherapp.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "main",
        "wind",
        "sys"
})
@Entity(tableName = "city")
public class City implements Serializable {

    @JsonProperty("id")
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @JsonProperty("name")
    @ColumnInfo(name = "name")
    private String name;
    @JsonProperty("main")
    @Embedded(prefix = "main")
    private Main main;
    @JsonProperty("sys")
    @Embedded(prefix = "sys")
    private Sys sys;
    @JsonProperty("wind")
    @Embedded(prefix = "wind")
    private Wind wind;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("main")
    public Main getMain() {
        return main;
    }

    @JsonProperty("main")
    public void setMain(Main main) {
        this.main = main;
    }

    @JsonProperty("sys")
    Sys getSys() {
        return sys;
    }

    @JsonProperty("sys")
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    @JsonProperty("wind")
    public Wind getWind() {
        return wind;
    }

    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }
}

