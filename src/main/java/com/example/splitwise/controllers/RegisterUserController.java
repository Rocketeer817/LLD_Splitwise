package com.example.splitwise.controllers;

import com.example.splitwise.commands.RegisterUserCommand;
import com.example.splitwise.dto.RegisterResponseStatus;
import com.example.splitwise.dto.RegisterUserRequestDto;
import com.example.splitwise.dto.RegisterUserResponseDto;
import com.example.splitwise.exceptions.UserAlreadyPresentException;
import com.example.splitwise.services.RegisterUserService;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterUserController {
    private RegisterUserService registerUserService;

    public RegisterUserController(RegisterUserService registerUserService){
        this.registerUserService = registerUserService;
    }


    public RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto) throws Exception {

        RegisterUserResponseDto responseDto = new RegisterUserResponseDto();
        try {
            registerUserService.registerUser(registerUserRequestDto.getName(), registerUserRequestDto.getPhoneNo(), registerUserRequestDto.getPassword());
        } catch (UserAlreadyPresentException e) {
            responseDto.setRegisterResponseStatus(RegisterResponseStatus.ALREADY_PRESENT);
        }
        catch(Exception e){
            responseDto.setRegisterResponseStatus(RegisterResponseStatus.UNSUCCESSFULL);
        }

        responseDto.setRegisterResponseStatus(RegisterResponseStatus.SUCCESSFULL);

        return responseDto;

    }

}

