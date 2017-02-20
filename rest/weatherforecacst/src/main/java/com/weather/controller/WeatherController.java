package com.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weather.dao.WeatherDao;
import com.weather.dao.WeatherDaoImpl;
import com.weather.model.WeatherData;

@RestController
public class WeatherController {

	@Autowired
	WeatherDao weatherDao;

	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public String inputWeatherData(@RequestBody WeatherData input) {

		//weatherDao = new WeatherDaoImpl();
		weatherDao.insertData(input);

		return "ok";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(ModelMap input) {

		return "Service is UP.";
	}

}
