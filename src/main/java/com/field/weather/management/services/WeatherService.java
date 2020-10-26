package com.field.weather.management.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.field.weather.management.entity.CreatePolygon;
import com.field.weather.management.entity.Field;
import com.field.weather.management.entity.Weather;
import com.field.weather.management.entity.WeatherCast;
import com.field.weather.management.exception.FieldNotFoundException;
import com.field.weather.management.repositories.Constants;
import com.field.weather.management.repositories.feign.client.PolygonRepository;
import com.field.weather.management.repositories.feign.client.WeatherRepository;

@Service
public class WeatherService implements Constants{

	@Autowired
	private FieldService fieldService;
	
	@Autowired
	private WeatherRepository weatherRepository;
	
	@Autowired
	private PolygonRepository polygonRepository;
	
	public List<Weather> getWeather(Long id){
		Field field = fieldService.getField(id);
		if(field == null){
			throw new FieldNotFoundException("Field not found with this id", Field.builder().fieldid(id).build());
		} else {
			return weatherRepository.getWeather(polygonRepository.createPolygon(CreatePolygon.builder().geo_json(field.getBoundaries().getGeoJson()).name(field.getName()).build(), API_KEY).getId(), API_KEY,  getNowEpoch(), getSevenDayBeforeEpoch()).stream().map(w -> getWeather(w)).collect(Collectors.toList());
		}
	}
	
	
	private static Weather getWeather(WeatherCast w){
		return Weather
					.builder()
						.humidity(w.getMain().getHumidity())
						.temperatureMin(w.getMain().getTemp_min())
						.temperatureMax(w.getMain().getTemp_max())
						.timestamp(String.valueOf(w.getDt()))
						.temperature(w.getMain().getTemp())
					.build();
	}
	
	private static long getNowEpoch(){
		LocalDateTime localDateTime = LocalDateTime.now();
		ZoneId id = ZoneId.systemDefault();
		return localDateTime.atZone(id).toEpochSecond();
	}
	
	private static long getSevenDayBeforeEpoch(){
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime sevenDayBefore = localDateTime.minusDays(7);
		ZoneId id = ZoneId.systemDefault();
		return sevenDayBefore.atZone(id).toEpochSecond();
	}
}