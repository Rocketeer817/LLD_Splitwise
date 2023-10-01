package com.example.splitwise.strategy;

public interface HashingStrategy {
    String hashPassword(String password);
    boolean matchPassword(String password, String hashedPassword);
}
