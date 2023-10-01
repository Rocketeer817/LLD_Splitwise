package com.example.splitwise.services;

import com.example.splitwise.models.Expense;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettleUpService {
    public List<Expense> settleUpGroup(Long groupId){
        ArrayList<Expense> arrayList = new ArrayList<>();

        /*
        * 1. Get all the expenses associated with the group. Throw an exception if group is not present
        * 2. Use HeapSettleUpStrategy for the
        * */

        return new ArrayList<>();
    }
}
