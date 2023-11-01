package com.sarmad.task.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "user_car")
@Data
public class UserCar {
	@Transient
	public static final String SEQUENCE_NAME = "user_car_sequence";
	
	@Id
	private long userCarId;
	 
	private String userId;
	
	private String carModelId;
	
	private String carePlateNo;
	
	private String color;
	
	
}
