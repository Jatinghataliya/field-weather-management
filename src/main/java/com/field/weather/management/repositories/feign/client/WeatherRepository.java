package com.field.weather.management.repositories.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.field.weather.management.entity.WeatherCast;

@FeignClient(name = "weather-repo", url = "http://api.agromonitoring.com/")
public interface WeatherRepository {
	
	@RequestMapping(value = "/agro/1.0/weather/history", method = RequestMethod.GET, produces = "application/json")
	List<WeatherCast> getWeather(@RequestParam("polyid") String polyId, @RequestParam("appid") String appId, @RequestParam("start") long start, @RequestParam("end") long end);
}