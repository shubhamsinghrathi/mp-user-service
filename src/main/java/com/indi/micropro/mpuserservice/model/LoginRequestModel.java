package com.indi.micropro.mpuserservice.model;

import lombok.Data;

@Data
public class LoginRequestModel {
	private String email;
	private String password;
}
