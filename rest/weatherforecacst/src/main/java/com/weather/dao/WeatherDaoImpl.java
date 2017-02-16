package com.weather.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.weather.model.WeatherData;

@Repository
public class WeatherDaoImpl implements WeatherDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private static final class WeatherMapper implements RowMapper<WeatherData> {

		public WeatherData mapRow(ResultSet rs, int rowNum) throws SQLException {
			WeatherData data = new WeatherData();
			data.setCity(rs.getString("city"));
			data.setDescription(rs.getString("description"));

			return data;
		}
	}

	@Override
	public void insertData(WeatherData input) {

		try{
		String sql = "INSERT INTO WeatherData(city,description,humidity,pressure,temperature,creationtime,speed,degree) values(?,?,?,?,?,?,?,?)";

		namedParameterJdbcTemplate.batchUpdate(sql,
				(Map<String, ?>[]) new Object[] { input.getCity(), input.getDescription(), input.getHumidity(),
						input.getPressure(), input.getTemperature(), input.getTimestamp(), input.getWind().getSpeed(),
						input.getWind().getDegree() });
		
		}catch(Exception e){
			System.out.println("Insertion failed.");
		}

	}

	@Override
	public List<WeatherData> getData() {
		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM WeatherData";

		List<WeatherData> result = namedParameterJdbcTemplate.query(sql, params, new WeatherMapper());

		return result;
	}

}