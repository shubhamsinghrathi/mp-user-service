package com.indi.micropro.mpuserservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.indi.micropro.mpuserservice.shared.UserDto;

public interface UsersService extends UserDetailsService {
	UserDto createUser(UserDto userDetails);
	UserDto getUserDetailsByEmail(String email);
	UserDto getUserByUserId(String userId);
}
