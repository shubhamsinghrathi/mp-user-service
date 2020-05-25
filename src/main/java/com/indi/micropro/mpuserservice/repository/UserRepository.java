package com.indi.micropro.mpuserservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.indi.micropro.mpuserservice.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String username);
	UserEntity findByUserId(String userId);
	
}
