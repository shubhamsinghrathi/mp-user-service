package com.indi.micropro.mpuserservice.model;

import java.util.List;

import lombok.Data;

@Data
public class UserResponseModel {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumResponseModel> albums;
}
