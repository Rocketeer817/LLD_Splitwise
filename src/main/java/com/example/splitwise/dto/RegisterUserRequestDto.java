package com.example.splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequestDto {
    private String name;
    private String phoneNo;
    private String password;
}
