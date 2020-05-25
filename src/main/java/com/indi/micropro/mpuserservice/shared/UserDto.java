package com.indi.micropro.mpuserservice.shared;

import java.io.Serializable;
import java.util.List;

import com.indi.micropro.mpuserservice.model.AlbumResponseModel;

import lombok.Data;

@Data
public class UserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -146975768640396888L;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String userId;
	private String encryptedPassword;
	private List<AlbumResponseModel> albums;
}
