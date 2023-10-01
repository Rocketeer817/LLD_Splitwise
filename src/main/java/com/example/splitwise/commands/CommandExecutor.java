package com.example.splitwise.commands;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class CommandExecutor {
    private List<Command> commands;

    @Autowired
    public CommandExecutor(List<Command> commands) {
        this.commands = commands;
    }


    public void addCommand(Command command){
        commands.add(command);
    }

    public void removeCommand(Command command){
        commands.remove(command);
    }

    public void execute(String input) {
        for(Command command : commands){
            if(command.matches(input)){
                command.execute(input);
            }
        }
    }
}
