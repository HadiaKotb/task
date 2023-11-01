package com.sarmad.task.dataseeder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.sarmad.task.entities.CarModel;
import com.sarmad.task.reposiotry.CarModelRepository;
import com.sarmad.task.reposiotry.UserRepository;
import com.sarmad.task.service.ICarModel;
import com.sarmad.task.service.ISequenceGenerator;

@Component
public class UserCarDataSeeder implements ApplicationRunner {

	@Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarModelRepository carModelRepository;
    
    @Autowired
	ISequenceGenerator sequenceGenerator;
    
    @Autowired
    private ICarModel carModelService;
    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		  if (!dataAlreadyExistsInCarModelCollection()) {
	            insertDataIntoCarModel();
	        }
		
	}

    private void insertDataIntoCarModel() {
		// TODO Auto-generated method stub
    	List<CarModel> cars = List.of( 
    			new CarModel(sequenceGenerator.generateSequence(CarModel.SEQUENCE_NAME),
    			"Lancer","Mitsbushi",2023),
    			new CarModel(sequenceGenerator.generateSequence(CarModel.SEQUENCE_NAME),
    	    			"tibo","Fiat",2023),
    			new CarModel(sequenceGenerator.generateSequence(CarModel.SEQUENCE_NAME),
    	    			"revuelto","Lambergini",2023),
    			new CarModel(sequenceGenerator.generateSequence(CarModel.SEQUENCE_NAME),
    	    			"LandCrusier","Toyta",2023),
    			new CarModel(sequenceGenerator.generateSequence(CarModel.SEQUENCE_NAME),
    	    			"Bicanto","Kia",2023),
    			new CarModel(sequenceGenerator.generateSequence(CarModel.SEQUENCE_NAME),
    	    			"Minicouper","Minicouper",2023)
    			);
    	carModelService.saveCar(cars);
		
	}

	private boolean dataAlreadyExistsInUserCarCollection() {
        // Check if there are any records in the USER_CARS collection
        return mongoTemplate.count(new Query(), "user_car") > 0;
    }
    private boolean dataAlreadyExistsInCarModelCollection() {
        // Check if there are any records in the USER_CARS collection
        return mongoTemplate.count(new Query(), "car_model") > 0;
    }
}
