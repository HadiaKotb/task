package com.sarmad.task.service;

import com.sarmad.task.entities.User;
import com.sarmad.task.response.LoginResponse;
import com.sarmad.task.response.UserResponse;

public interface IUser {
	
	public UserResponse registerUser(User request);

	public LoginResponse login(String loginId, String password);

}
