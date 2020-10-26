package com.field.weather.management.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.field.weather.management.entity.Field;
import com.field.weather.management.services.FieldService;
import com.field.weather.management.utils.TestHelper;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FieldControllerIT.class)
public class FieldControllerTests {

	@MockBean
	FieldService fieldService;

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	Field existingField, newField, updateField;

	@Before
	public void setUp() {
		newField = TestHelper.buildField();
		existingField = TestHelper.buildField();
		updateField = TestHelper.buildField();
	}

	@Test
	public void should_get_all_fields() throws Exception {
		given(fieldService.getFields()).willReturn(Arrays.asList(existingField, updateField));

		this.mockMvc.perform(get("/api/fields")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void should_get_field_by_id() throws Exception {
		given(fieldService.getField(existingField.getFieldid())).willReturn(existingField);

		this.mockMvc.perform(get("/api/fields/" + existingField.getFieldid())).andExpect(status().isOk())
				.andExpect(jsonPath("$.fieldid", is(existingField.getFieldid())))
				.andExpect(jsonPath("$.name", is(existingField.getName())));
	}

	@Test 
	public void should_create_field() throws Exception {
		given(fieldService.save(newField)).willReturn(newField);
		this
			.mockMvc
			.perform(post("/api/fields")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(newField))) 
	  		.andExpect(status().isCreated()) 
	  		.andExpect(jsonPath("$.fieldid", notNullValue()))
	  		.andExpect(jsonPath("$.name", is(newField.getName()))); 
	}

	@Test
	public void should_update_field() throws Exception {
		given(fieldService.update(existingField)).willReturn(existingField);

		this.mockMvc
				.perform(put("/api/fields")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(existingField)))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.fieldid", is(existingField.getFieldid())))
						.andExpect(jsonPath("$.name", is(existingField.getName())));
	}

}