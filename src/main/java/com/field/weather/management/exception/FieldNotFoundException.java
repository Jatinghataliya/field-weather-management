package com.field.weather.management.exception;

import org.springframework.stereotype.Component;

import com.field.weather.management.entity.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class FieldNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private Field field;
	
	public FieldNotFoundException() {
	}

	public FieldNotFoundException(String message, Field field) {
		super(message);
		this.message = message;
		this.field = field;
	}

	public FieldNotFoundException(Throwable cause) {
		super(cause);
	}

	public FieldNotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public FieldNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.message = message;
	}

}