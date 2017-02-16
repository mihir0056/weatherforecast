package com.weather.dao;

import java.util.List;

import com.weather.model.WeatherData;

public interface WeatherDao {

	void insertData(WeatherData input);
	
	List<WeatherData> getData();

}