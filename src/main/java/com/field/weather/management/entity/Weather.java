package com.field.weather.management.entity;


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
public class Weather {

	private String timestamp;
	private double temperature;
	private double humidity;
	private double temperatureMax;
	private double temperatureMin;
}
