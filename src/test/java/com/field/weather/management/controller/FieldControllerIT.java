package com.field.weather.management.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.field.weather.management.entity.Field;
import com.field.weather.management.repositories.FieldRepository;
import com.field.weather.management.utils.BaseIntegrationTest;
import com.field.weather.management.utils.TestHelper;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class FieldControllerIT extends BaseIntegrationTest {

	@Autowired
	FieldRepository fieldRepository;
	@Autowired
	TestRestTemplate restTemplate;

	Field existingField, newField, updatedField;

	@Before
	public void setUp() {
		newField = TestHelper.buildField();

		existingField = TestHelper.buildField();
		existingField = fieldRepository.save(existingField);

		updatedField = TestHelper.buildField();
		updatedField = fieldRepository.save(updatedField);
	}

	@After
	public void tearDown() {
		if (newField.getId() != null) {
			fieldRepository.deleteById(newField.getFieldid());
		}
		fieldRepository.deleteAll(fieldRepository.findAllById(asList(existingField.getFieldid(), updatedField.getFieldid())));
	}

	@Test
	public void should_get_all_fields() {
		ResponseEntity<Field[]> responseEntity = restTemplate.getForEntity("/api/fields", Field[].class);
		List<Field> Fields = asList(responseEntity.getBody());
		assertThat(Fields).isNotEmpty();
	}

	@Test
	public void should_get_field_by_id() {
		ResponseEntity<Field> responseEntity = restTemplate.getForEntity("/api/fields/" + existingField.getFieldid(), Field.class);
		Field Field = responseEntity.getBody();
		assertThat(Field).isNotNull();
	}

	@Test
	public void should_create_field() {
		HttpEntity<Field> request = new HttpEntity<>(newField);
		ResponseEntity<Field> responseEntity = restTemplate.postForEntity("/api/fields", request, Field.class);
		Field savedField = responseEntity.getBody();
		assertThat(savedField.getId()).isNotNull();
	}

	@Test
	public void should_update_field() {
		HttpEntity<Field> request = new HttpEntity<>(updatedField);
		restTemplate.put("/api/fields", request, Field.class);
		ResponseEntity<Field> responseEntity = restTemplate.getForEntity("/api/fields", Field.class);
		Field updatedField = responseEntity.getBody();
		assertThat(updatedField.getId()).isEqualTo(updatedField.getId());
	}

	@Test
	public void should_delete_field() {
		ResponseEntity<Field> responseEntity = restTemplate.getForEntity("/api/fields",Field.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
		restTemplate.delete("/api/fields");
		responseEntity = restTemplate.getForEntity("/api/fields", Field.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(NOT_FOUND);
	}
}