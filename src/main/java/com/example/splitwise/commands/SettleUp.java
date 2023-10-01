package com.example.splitwise.commands;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SettleUp implements Command{

    /*
    * user1 SettleUp
    * */
    @Override
    public boolean matches(String s) {
        if(s.isEmpty() || s.isBlank()){
            return false;
        }

        List<String> words = Arrays.stream(s.split(" ")).toList();

        if(words.size()==2 && words.get(1).equals(CommandName.settleUp)){
            return true;
        }

        return false;
    }

    @Override
    public void execute(String s) {

    }
}
