package com.indi.micropro.mpuserservice.service;

import com.indi.micropro.mpuserservice.shared.UserDto;

public interface UsersService {
	UserDto createUser(UserDto userDetails);
	UserDto getUserDetailsByEmail(String email);
	UserDto getUserByUserId(String userId);
}
