package com.field.weather.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.field.weather.management.entity.Field;
import com.field.weather.management.entity.Response;
import com.field.weather.management.entity.Weather;
import com.field.weather.management.services.FieldService;
import com.field.weather.management.services.WeatherService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class FieldController {

	@Autowired
	private FieldService fieldService;
	
	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/fields")
	public List<Field> getFields(){
		log.info("process=get-fields");
		return fieldService.getFields();
	}
	
	@PostMapping("/fields")
	public Field save(@RequestBody Field field) {
		log.info("process=save-field, field={}", field.toString());
		return fieldService.save(field);
	}
	
	@DeleteMapping("/fields")
	public String delete(@RequestBody Field field){
		log.info("process=delete-field, field={}", field.toString());
		fieldService.delete(field);
		return ResponseEntity.ok().toString();
	}
	
	@GetMapping("/fields/{fieldid}")
	public Field getField(@PathVariable("fieldid") Long id){
		log.info("process=get-field, id={}", id);
		return fieldService.getField(id);
	}
	
	@GetMapping("/fields/{fieldid}/weather")
	public Response<List<Weather>> weather(@PathVariable("fieldid") Long id){
		log.info("process=weather-info,  id={}", id);
		return Response.<List<Weather>>builder().data(weatherService.getWeather(id)).build();
	}
}