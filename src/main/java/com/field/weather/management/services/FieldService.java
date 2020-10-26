package com.field.weather.management.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.field.weather.management.entity.Field;
import com.field.weather.management.repositories.FieldRepository;

@Service
public class FieldService {

	@Autowired
	private FieldRepository fieldRepository;
	
	public List<Field> getFields(){
		return fieldRepository.findAll();
	}

	public Field save(Field field){
		return fieldRepository.save(field);
	}
	
	public Field update(Field field){
		Field foundedField = fieldRepository.findFieldByFieldId(field.getFieldid());
		foundedField.setBoundaries(field.getBoundaries());
		foundedField.setCountryCode(field.getCountryCode());
		foundedField.setName(field.getName());
		foundedField.setUpdatedAt(LocalDateTime.now());
		return fieldRepository.save(field);
	}
	
	public Field getField(Long id){
		return fieldRepository.findFieldByFieldId(id);
	}
	
	public void delete(Field field){
		fieldRepository.delete(field);
	}
	
}