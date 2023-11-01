package com.sarmad.task.reposiotry;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sarmad.task.entities.UserCar;

public interface UserCarRepository extends MongoRepository<UserCar, String> {

}
