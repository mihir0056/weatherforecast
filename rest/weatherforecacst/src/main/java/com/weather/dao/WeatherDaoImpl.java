package com.weather.dao;

import com.weather.dao.WeatherDao;
import com.weather.dao.WeatherDaoImpl;
import com.weather.model.WeatherData;
import com.weather.model.Wind;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class WeatherDaoImpl
implements WeatherDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertData(WeatherData input) {
        try {
            String sql = "INSERT INTO WeatherData(city,description,humidity,pressure,temperature,creationtime,speed,degree) values(?,?,?,?,?,?,?,?)";
            this.jdbcTemplate.update(sql, new Object[]{input.getCity(), input.getDescription(), input.getHumidity(), input.getPressure(), input.getTemperature(), input.getTimestamp(), input.getWind().getSpeed(), input.getWind().getDegree()});
        }
        catch (Exception e) {
            System.out.println("Insertion failed." + e);
            return false;
        }
        return true;
    }

    @Override
    public List<WeatherData> getData() {
        HashMap params = new HashMap();
        String sql = "SELECT * FROM WeatherData";
        List result = this.jdbcTemplate.query(sql, (RowMapper)new WeatherMapper());
        return result;
    }

    @Override
    public List<WeatherData> getCityData(String cityName) {
        String sql = "SELECT * FROM WeatherData where city='" + cityName + "'";
        List result = this.jdbcTemplate.query(sql, (RowMapper)new WeatherMapper());
        return result;
    }
}