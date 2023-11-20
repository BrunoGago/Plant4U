package com.fiap.Plant4U.dtos;

import lombok.Data;

@Data
public class UserEventDto {

    private Long userId;
    private String username;
    private String email;
    private String fullName;
    private String userStatus;
    private String userType;
    private String phoneNumber;
    private String cpf;
    private String imageUrl;
    private String actionType;
}
