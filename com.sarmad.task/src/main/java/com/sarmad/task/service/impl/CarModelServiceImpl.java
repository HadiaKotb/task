package com.sarmad.task.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarmad.task.entities.CarModel;
import com.sarmad.task.reposiotry.CarModelRepository;
import com.sarmad.task.service.ICarModel;
import com.sarmad.task.service.ISequenceGenerator;
@Service
public class CarModelServiceImpl implements ICarModel {
	
	@Autowired
	CarModelRepository carModelRepo;
	
	@Autowired
	ISequenceGenerator sequenceGenerator;
	

	@Override
	public List<CarModel> saveCar(List<CarModel> car) {
		// TODO Auto-generated method stub
		
		  
		return carModelRepo.saveAll(car);
	}

}
