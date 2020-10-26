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
public class Main {
	
	private double temp;
	private double pressure;
	private double humidity;
	private double temp_min;
	private double temp_max;

}