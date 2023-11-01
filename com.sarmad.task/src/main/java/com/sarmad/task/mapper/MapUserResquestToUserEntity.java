package com.sarmad.task.mapper;

import com.sarmad.task.entities.User;
import com.sarmad.task.request.UserRequest;

public class MapUserResquestToUserEntity {

	public static User mapUserResquestToUserEntity(UserRequest userRequest) {
		
		User user= new User();
		user.setFirstName(userRequest.getFirstName());
		user.setSecondName(userRequest.getLastName());
		user.setLoginId(userRequest.getLoginId());
		user.setPassword(userRequest.getPassword());
		
		return user;
		
		
	}
}
