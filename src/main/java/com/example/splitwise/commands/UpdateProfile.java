package com.example.splitwise.commands;

import org.springframework.stereotype.Component;

@Component
public class UpdateProfile implements Command{
    @Override
    public boolean matches(String s) {
        return false;
    }

    @Override
    public void execute(String s) {

    }
}
