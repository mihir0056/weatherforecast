package com.weather.controller;

import com.weather.dao.WeatherDao;
import com.weather.model.WeatherData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.PrintStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="weatherdata")
@RequestMapping(value={"/api"})

public class WeatherController {
    @Autowired
    WeatherDao weatherDao;

    @RequestMapping(value={"/input"}, method={RequestMethod.POST})
    @ApiOperation(value="insert city weather data", notes="returns ok if successful", response=HttpStatus.class)
    @ResponseBody
    public HttpStatus inputWeatherData(@RequestBody WeatherData input) {
        try {
            boolean isSucess = this.weatherDao.insertData(input);
            if (isSucess) {
                return HttpStatus.CREATED;
            }
        }
        catch (Exception isSucess) {
            // empty catch block
        }
        return HttpStatus.EXPECTATION_FAILED;
    }

    @RequestMapping(value={"/{city}"}, method={RequestMethod.GET})
    @ApiOperation(value="get weather of the city", notes="city weather service", response=String.class)
    @ResponseBody
    public List<WeatherData> getCityWeather(@PathVariable(value="city") String city) {
        List list = this.weatherDao.getCityData(city);
        System.out.println(list);
        return list;
    }

    @RequestMapping(value={"/allcity"}, method={RequestMethod.GET})
    @ApiOperation(value="get weather of all cities", notes="city weather service", response=String.class)
    @ResponseBody
    public List<WeatherData> getAllCityWeather() {
        List list = this.weatherDao.getData();
        System.out.println(list);
        return list;
    }

    @RequestMapping(value={"/test"}, method={RequestMethod.GET})
    @ApiOperation(value="test if service is up", notes="test service", response=String.class)
    @ResponseBody
    public String test(ModelMap input) {
        return "Service is UP.";
    }
}