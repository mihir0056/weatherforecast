package com.weather.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.weather.model.WeatherData;

@Repository
public class WeatherDaoImpl implements WeatherDao {

	@Autowired 
	private DataSource dataSource;
	 
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
   /* public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }*/

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
			//JdbcTemplate empDAO = applicationContext.getBean(EmployeeDAO.class);	
			
		String sql = "INSERT INTO WeatherData(city,description,humidity,pressure,temperature,creationtime,speed,degree) values(?,?,?,?,?,?,?,?)";

		jdbcTemplate.update(sql,new Object[] { input.getCity(), input.getDescription(), input.getHumidity(),
						input.getPressure(), input.getTemperature(), input.getTimestamp(), input.getWind().getSpeed(),
						input.getWind().getDegree() });
		
		}catch(Exception e){
			System.out.println("Insertion failed."+e);
		}

	}

	@Override
	public List<WeatherData> getData() {
		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM WeatherData";
		
		List<WeatherData> result = jdbcTemplate.query(sql, new WeatherMapper());

		return result;
	}

}