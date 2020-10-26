package com.field.weather.management.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.field.weather.management.exception.FieldNotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {


	@org.springframework.web.bind.annotation.ExceptionHandler(value = FieldNotFoundException.class)
	public ResponseEntity<String> handle(FieldNotFoundException fieldNotFoundException){
		log.error("error=field-not-avaialble,  field={}", fieldNotFoundException.getField());
		return new ResponseEntity<String>(fieldNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}