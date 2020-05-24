package com.indi.micropro.mpuserservice.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indi.micropro.mpuserservice.entity.UserEntity;
import com.indi.micropro.mpuserservice.repository.UserRepository;
import com.indi.micropro.mpuserservice.shared.UserDto;

@Service
public class UsersServiceImpl implements UsersService {
	
	private UserRepository UserRepository;
	
	@Autowired
	public UsersServiceImpl(UserRepository userRepository) {
		this.UserRepository = userRepository;
	}

	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
		
		userEntity.setEncryptedPassword(UUID.randomUUID().toString());
		UserRepository.save(userEntity);
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
		return returnValue;
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
