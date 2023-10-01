package com.example.splitwise.commands;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RegisterUserCommand implements Command{

    /*
   Expected Input :-

       Register <userName> <phoneNumber> <password>
       Register nikhil 1234 password
       register nikhil 1234 password
    */

    @Override
    public boolean matches(String s) {
        if(s.isEmpty() || s.isBlank()){
            return false;
        }

        List<String> words = Arrays.stream(s.split(" ")).toList();

        if(words.size()==4 && words.get(0).equals(CommandName.registerUser)){
            return true;
        }

        return false;
    }

    @Override
    public void execute(String s) {

    }
}
