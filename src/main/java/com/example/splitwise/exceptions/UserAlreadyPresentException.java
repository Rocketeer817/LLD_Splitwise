package com.example.splitwise.exceptions;

public class UserAlreadyPresentException extends Exception{
    public UserAlreadyPresentException(String msg){
        super(msg);
    }
}
