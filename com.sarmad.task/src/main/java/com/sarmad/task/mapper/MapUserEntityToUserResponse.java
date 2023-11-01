package com.sarmad.task.mapper;

import org.springframework.http.HttpStatus;

import com.sarmad.task.entities.User;
import com.sarmad.task.response.UserResponse;

public class MapUserEntityToUserResponse {
	
	public static UserResponse mapUserEntityToUserResponse(User user) {
		
		UserResponse userResponse= new UserResponse();
		userResponse.setSuccessCode("201");
		userResponse.setSuccessMessage("your user is registered successfully");
		userResponse.setLoginId(user.getLoginId());
		userResponse.setUserId(user.getUserId());
		
		return userResponse;
		
		
	}

}
