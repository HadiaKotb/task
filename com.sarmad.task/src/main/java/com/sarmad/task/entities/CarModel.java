package com.sarmad.task.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document(collection = "car_model")
@Data
@AllArgsConstructor
public class CarModel {
	@Transient
	public static final String SEQUENCE_NAME = "car_model_sequence";
	@Id
	private long carModelId;
	
	private String modelName;
	
	private String type;
	
	private Integer year;
	
	
	
}
