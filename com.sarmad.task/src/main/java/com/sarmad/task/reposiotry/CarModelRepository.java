package com.sarmad.task.reposiotry;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sarmad.task.entities.CarModel;

public interface CarModelRepository extends MongoRepository<CarModel, String> {

}
