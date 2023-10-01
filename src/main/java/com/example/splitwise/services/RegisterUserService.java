package com.example.splitwise.services;

import com.example.splitwise.exceptions.UserAlreadyPresentException;
import com.example.splitwise.models.User;
import com.example.splitwise.repositories.UserRepository;
import com.example.splitwise.strategy.HashingStrategy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUserService {

    private UserRepository userRepository;

    private HashingStrategy hashingStrategy;

    public RegisterUserService(UserRepository userRepository,HashingStrategy hashingStrategy){
        this.userRepository = userRepository;
        this.hashingStrategy = hashingStrategy;
    }

    public boolean registerUser(String name, String phoneNo, String password) throws UserAlreadyPresentException{
        /*
        * Assumption : only 1 phone no is allowed and duplicate phone No for registering another user is not allowed
        *
        * Check if user is already present in db. If present, return Already present
        *
        * If user not present, convert password into decrypt decode.
        *
        * Then insert this into user table .
        *
        * Check if this is successfull, return Success then, else return unsuccesfull.
        * */

        Optional<User> user = userRepository.findByPhoneNo(phoneNo);

        if(user.isEmpty()==false){
            throw new UserAlreadyPresentException("user already present");
        }

        User newuser = new User();
        newuser.setName(name);
        newuser.setPassword(hashingStrategy.hashPassword(password));
        newuser.setPhoneNo(phoneNo);
        User k= userRepository.save(newuser);

        return true;
    }
}
