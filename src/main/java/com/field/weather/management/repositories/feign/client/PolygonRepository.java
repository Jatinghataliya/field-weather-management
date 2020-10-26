package com.field.weather.management.repositories.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.field.weather.management.entity.CreatePolygon;
import com.field.weather.management.entity.PolygonResponse;

@FeignClient(name = "open-weather-management", url = "http://api.agromonitoring.com/")
public interface PolygonRepository {

	@RequestMapping(value = "/agro/1.0/polygons", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	PolygonResponse createPolygon(@RequestBody CreatePolygon createPolygon, @RequestParam(value = "appid") String appId);
	
	@RequestMapping(value = "/agro/1.0/polygons/{id}", method = RequestMethod.GET, produces = "application/json")
	PolygonResponse getPolygon(@PathVariable("id") String id, @RequestParam(value = "appid") String appId);
	
	@RequestMapping(value = "/agro/1.0/polygons", method = RequestMethod.GET, produces = "application/json")
	List<PolygonResponse> getPolygons(@RequestParam(value = "appid") String appId);
}