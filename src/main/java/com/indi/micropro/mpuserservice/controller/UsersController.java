package com.indi.micropro.mpuserservice.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indi.micropro.mpuserservice.model.CreateUserRequestModel;
import com.indi.micropro.mpuserservice.model.CreateUserResponseModel;
import com.indi.micropro.mpuserservice.model.UserResponseModel;
import com.indi.micropro.mpuserservice.service.UsersService;
import com.indi.micropro.mpuserservice.shared.UserDto;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private Environment env;

	@Autowired
	private UsersService usersService;

	@GetMapping("/status/check")
	public String status() {
		return "working on port: " + env.getProperty("local.server.port") + " || Test key: " + env.getProperty("testing.key");
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetail) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(userDetail, UserDto.class);
		UserDto createdUser = usersService.createUser(userDto);
		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@GetMapping(value="/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {
       
        UserDto userDto = usersService.getUserByUserId(userId); 
        UserResponseModel returnValue = new ModelMapper().map(userDto, UserResponseModel.class);
        
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

}
