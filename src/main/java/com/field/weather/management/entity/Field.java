package com.field.weather.management.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

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
@Entity
@Table(name = "field")
public class Field {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fieldid;
	
	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name = "field_name", nullable = false)
	private String name;
	
	@Column(name = "country_code", nullable = false)
	private String countryCode;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "boundaryid", referencedColumnName = "fieldid")
	private Boundaries boundaries;
	
	//@Convert(converter = LocalDateTimeAttributeConverter.class)
	@Column(name = "created", nullable = false)
    private LocalDateTime createdAt;

	//@Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "updated", nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    void preSave() {
        if(createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    void preUpdate() {
        if(updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }
}