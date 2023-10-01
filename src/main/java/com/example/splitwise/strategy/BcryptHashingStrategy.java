package com.example.splitwise.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class BcryptHashingStrategy implements HashingStrategy{

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public BcryptHashingStrategy(BCryptPasswordEncoder bcryptPasswordEncoder){
        this.bCryptPasswordEncoder = bcryptPasswordEncoder;
    }

    @Override
    public String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public boolean matchPassword(String password, String hashedPassword) {
        return bCryptPasswordEncoder.matches(password,hashedPassword);
    }
}
