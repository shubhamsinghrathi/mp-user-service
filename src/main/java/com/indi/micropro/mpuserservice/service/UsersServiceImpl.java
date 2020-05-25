package com.indi.micropro.mpuserservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.indi.micropro.mpuserservice.entity.UserEntity;
import com.indi.micropro.mpuserservice.model.AlbumResponseModel;
import com.indi.micropro.mpuserservice.repository.UserRepository;
import com.indi.micropro.mpuserservice.shared.UserDto;

@Service
public class UsersServiceImpl implements UsersService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	// private RestTemplate restTemplate;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AlbumsServiceClient albumsServiceClient;

	@Autowired
	public UsersServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		// this.restTemplate = restTemplate;
	}

	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		userRepository.save(userEntity);
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
		return returnValue;
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException("User not found");

		UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

//		String albumsUrl = String.format(environment.getProperty("albums.url"), userId);
//
//		ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET,
//				null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {
//				});
//		List<AlbumResponseModel> albumsList = albumsListResponse.getBody();

		logger.info("Before calling albums Microservice");
		List<AlbumResponseModel> albumsList = albumsServiceClient.getAlbums(userId);
		logger.info("After calling albums Microservice");

		userDto.setAlbums(albumsList);

		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(username);
		if (userEntity == null)
			throw new UsernameNotFoundException(username);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
				new ArrayList<>());
	}

}
