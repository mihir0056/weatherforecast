package com.weather.test;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.weather.model.WeatherData;
import com.weather.model.Wind;
import com.weather.dao.WeatherDao;
import com.weather.dao.WeatherDaoImpl;



public class WeatherDaoTest {

    private EmbeddedDatabase db;

    WeatherDao weatherDao;
    
    @Before
    public void setUp() {
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
    	db = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.HSQL)
    		.addScript("db/sql/create-db.sql")
    		//.addScript("db/sql/insert-data.sql")
    		.build();
    }

    @Test
    public void testFindByname() {
    	JdbcTemplate template = new JdbcTemplate(db);
    	WeatherDaoImpl weatherDao = new WeatherDaoImpl();
    	//weatherDao.setJdbcTemplate(template);
    	
    	List<WeatherData> weatherData = weatherDao.getData();
    	
    	weatherData.forEach(name -> System.out.println(name.getCity()));
  
    	Assert.assertNotNull(weatherData);
    	

    }
    
    @Test
    public void insertData() {
    	JdbcTemplate template = new JdbcTemplate(db);
    	WeatherDaoImpl weatherDao = new WeatherDaoImpl();
    	//weatherDao.setJdbcTemplate(template);
    	
    	WeatherData wd = new WeatherData();
    	
    	wd.setCity("San Leandro");
    	wd.setDescription("City near san francisco");
    	wd.setHumidity("123");
    	wd.setPressure("12");
    	wd.setTemperature("10");
    	wd.setTimestamp("01/01/2017");
    	
    	Wind wind = new Wind();
    	wind.setDegree("123");
    	wind.setSpeed("124");
    	
    	wd.setWind(wind);
    	
    	weatherDao.insertData(wd);
    	
    	List<WeatherData> weatherData = weatherDao.getData();
    	
    	weatherData.forEach(name -> System.out.println(name.getCity()));
  
    	Assert.assertNotNull(weatherData);
    	

    }

    @After
    public void tearDown() {
        db.shutdown();
    }

}