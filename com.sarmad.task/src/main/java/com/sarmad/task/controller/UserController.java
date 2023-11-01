package com.sarmad.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarmad.task.mapper.MapUserResquestToUserEntity;
import com.sarmad.task.request.LoginRequest;
import com.sarmad.task.request.UserRequest;
import com.sarmad.task.response.LoginResponse;
import com.sarmad.task.response.UserResponse;
import com.sarmad.task.service.IUser;

@RestController
@RequestMapping("/sarmad-task/auth")
public class UserController {
	
	@Autowired
	IUser userService;
	
	 @PostMapping("/register")
	 public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest user) {
		 
		 return  ResponseEntity.ok(userService.registerUser(MapUserResquestToUserEntity.mapUserResquestToUserEntity(user)));
	 }
	 
	 
	 @PostMapping("/login")
	 public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		 
		 return  ResponseEntity.ok(userService.login(loginRequest.getLoginId(),loginRequest.getPassword()));
	 }
	 
	 @GetMapping("/HelloWorld")
	 public ResponseEntity<String> helloWorld() {
		 
		 return  ResponseEntity.ok("Hello World");
	 }
	
}
