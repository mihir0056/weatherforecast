package com.weather.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.weather.model.WeatherData;
import com.weather.model.Wind;

public class WeatherMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
       
		WeatherData data = new WeatherData();
        data.setCity(rs.getString("city"));
        data.setDescription(rs.getString("description"));
        data.setHumidity("humidity");
        data.setPressure("pressure");
        data.setTemperature("temperature");
        data.setTimestamp("timestamp");
        Wind wind = new Wind();
        wind.setDegree("degree");
        wind.setSpeed("speed");
        data.setWind(wind);
        
        return data;
    }
}
