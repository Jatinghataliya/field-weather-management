package com.field.weather.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.field.weather.management.entity.Field;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
	Field findFieldByFieldId(Long id);
}