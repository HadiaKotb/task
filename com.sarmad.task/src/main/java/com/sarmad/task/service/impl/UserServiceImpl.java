package com.sarmad.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sarmad.task.config.JwtService;
import com.sarmad.task.entities.User;
import com.sarmad.task.exception.UserAlreadyExistsException;
import com.sarmad.task.mapper.MapUserEntityToUserResponse;
import com.sarmad.task.reposiotry.UserRepository;
import com.sarmad.task.response.LoginResponse;
import com.sarmad.task.response.UserResponse;
import com.sarmad.task.service.ISequenceGenerator;
import com.sarmad.task.service.IUser;

@Service
public class UserServiceImpl implements IUser {

	@Autowired
	UserRepository userRepo;
	
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	 
	@Autowired 
	private JwtService jwtService;
	
	@Autowired
	ISequenceGenerator sequenceGenerator;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public UserResponse registerUser(User request) {
		// TODO Auto-generated method stub
		  if (userRepo.findByLoginId(request.getLoginId()) != null) {
	            throw new UserAlreadyExistsException("User with this login ID "+request.getLoginId()+" already exists.");
	      }
		  request.setPassword( passwordEncoder.encode(request.getPassword())); 
		  request.setUserId(sequenceGenerator.generateSequence(request.SEQUENCE_NAME));
		  
		  
		return MapUserEntityToUserResponse.mapUserEntityToUserResponse(userRepo.save(request));
	}

	@Override
	public LoginResponse login(String loginId, String password) {
		// TODO Auto-generated method stub
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginId, password));
		User user=userRepo.findByLoginId(loginId);
		String token=jwtService.generateToken(user);
		 
		return LoginResponse.builder()
				.token(token)
				.successMessage("User loged-in successfully")
				.build();
	}
	
}
