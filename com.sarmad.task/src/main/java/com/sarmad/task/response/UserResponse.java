package com.sarmad.task.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {

	private String successCode;
	
	private String successMessage;
	
	private long userId;
	

	
	private String loginId;
	
}
