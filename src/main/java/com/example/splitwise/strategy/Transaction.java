package com.example.splitwise.strategy;

import com.example.splitwise.models.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Transaction {
    private User user;
    private Integer amount;

    public Transaction(User user,Integer amount){
        this.user = user;
        this.amount = amount;
    }
}
