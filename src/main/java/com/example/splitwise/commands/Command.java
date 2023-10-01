package com.example.splitwise.commands;

public interface Command {
    boolean matches(String s);
    void execute(String s);
}
