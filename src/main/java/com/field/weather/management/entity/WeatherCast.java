package com.field.weather.management.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class WeatherCast {

	private long dt;
	private List<WeatherInner> weather;
	private Main main;
	private Wind wind;
	private Cloud clouds;
}