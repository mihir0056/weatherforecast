package com.weather.dao;

import java.util.List;

import com.weather.model.WeatherData;

public interface WeatherDao {
    public boolean insertData(WeatherData var1);

    public List<WeatherData> getData();

    public List<WeatherData> getCityData(String var1);
}