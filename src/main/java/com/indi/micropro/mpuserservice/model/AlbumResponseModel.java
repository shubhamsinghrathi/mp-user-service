package com.indi.micropro.mpuserservice.model;

import lombok.Data;

@Data
public class AlbumResponseModel {
    private String albumId;
    private String userId; 
    private String name;
    private String description;
}
